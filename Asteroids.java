
/**
 * Manuver a space ship in 3d space
 *
 * David Neufeld
 * 3/1/19
 */
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.event.*;
import java.io.*;
public class Asteroids extends JPanel implements KeyListener
{
    Universal u = new Universal();
    SpaceShip ship = new SpaceShip();
    Random r = new Random();
    javax.swing.Timer timer;
    double rotation=0;
    double rotationY=Math.PI;
    double x,y,z;
    double speedX;
    double speedZ;
    double speedY;
    double speed=40;
    double direction;
    int grid = 8;
    double zoom=1;
    boolean trail=true;
    boolean split=true;
    //Objects o = new Objects();

    Meteor meteor[] = new Meteor[100];
    int meteors=0;
    Ball particle[] = new Ball[300];
    int particles=0;
    //Meteor meteor[] = new Meteor[grid*grid];
    Ball bullet[] = new Ball[32];
    int bullets;
    ExplosionGif explosion[] = new ExplosionGif[32];
    int explosions;
    //double[] bulletDirection = new double[bullet.length];
    UFO ufo[] = new UFO[80];
    int ufos;
    /**
     * Constructor for objects of class SpaceFlight
     */
    public Asteroids(Color backColor) throws IOException
    {
        //System.out.println((int)u.camDistance((int)x,(int)y,(int)z)/2);
        // initialise instance variables
        setBackground(backColor);
        Random r = new Random();
        this.addKeyListener(this);
        this.setFocusable(true);
        int count=0;
        for(int i=0;i<grid;i++){
            for(int h=0;h<grid;h++){
                newMeteor(i*800*4,r.nextInt(6400*2),h*800*4,700);
                meteor[count].setSpeed(10);
                count++;
            }
        }

        newExplosion(0,0,0,10);
        y-=2000;

        timer = new javax.swing.Timer(5,new MoveListener());

    }

    double freeLook=0;
    int count=0;
    int c=0;
    //@Override
    double Health=100;
    int score = 0;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        //Finds Center of ship
        centerX = (int)(x+300+(30)* 
            Math.sin(rotation-ship.getRotation()-Math.PI/2));
        centerZ = (int)(z+300+(30)* 
            Math.cos(rotation-ship.getRotation()-Math.PI/2));
        centerY = (int)y+410;
        
        
        //Key Controlls
        direction=-(rotation)-Math.PI/2;
        if(keyD){
            if(ship.getRotation()>Math.PI/3+Math.PI)
                ship.changeRotation(-1*Math.PI/300);
            rotation+=Math.PI/300;
        } else if(keyA){
            if(ship.getRotation()<2*Math.PI/3+Math.PI)
                ship.changeRotation(Math.PI/300);
            rotation-=Math.PI/300;
        } else {
            if(ship.getRotation()>Math.PI/2+Math.PI){
                ship.changeRotation(-Math.PI/300);
            } else if(ship.getRotation()<Math.PI/2+Math.PI){
                ship.changeRotation(Math.PI/300);
            }
        }
        if(keyW &&Math.sqrt(speedX*speedX+speedZ*speedZ)<60*4) {
            //x+=Math.cos(direction)*speed;
            //z+=Math.sin(direction)*speed;
            speedX+=Math.cos(direction+ship.getRotation()+Math.PI/2);
            speedZ+=Math.sin(direction+ship.getRotation()+Math.PI/2);
            if (trail){
                newParticle
                ((int)(x+300+(160)*
                        Math.sin(rotation-ship.getRotation()-Math.PI/2))
                ,(int)y+410,(int)(z+300+(160)*
                        Math.cos(rotation-ship.getRotation()-Math.PI/2))
                ,8,Color.cyan);
            }
            count=0;
        }
        else if(keyS &&Math.sqrt(speedX*speedX+speedZ*speedZ)<60*4) {
            //x+=Math.cos(direction+Math.PI)*speed/2;
            //z+=Math.sin(direction+Math.PI)*speed/2;
            speedX-=Math.cos(direction);
            speedZ-=Math.sin(direction);
        }
        if(keySpace&&speedY>-30){
            y-=speed/2;
            speedY-=1.6;
            //rotationY-=Math.PI/360;
        } else if(keyShift&&speedY<30){
            y+=speed/2;
            speedY+=1.6;
            //rotationY+=Math.PI/360;
        }
        if(keyQ) freeLook-=Math.PI/300;
        else if(keyE) freeLook+=Math.PI/300;
        x+=speedX;
        z+=speedZ;
        y+=speedY;
        speedX-=speedX/100;
        speedZ-=speedZ/100;
        speedY-=speedY/100;

        //Delete Explosions
        for(int i=0;i<explosions;i++){
            if(explosion[i].expired())
                removeExplosion(i);
        }

        //set zoom based on speed
        speed =Math.sqrt(speedX*speedX+speedZ*speedZ);
        zoom=1-(speed/60)*0.2;

        //bullet Code
        //int bulletSpeed=500;
        for(int i=0;i<bullets;i++){
            //bullet[i].changeX(Math.cos(bulletDirection[i])*bullet[i].getSpeed());
            //bullet[i].changeZ(Math.sin(bulletDirection[i])*bullet[i].getSpeed());
            if(u.distance((int)x,(int)y,(int)z,(int)bullet[i].getX(),
                (int)bullet[i].getY(),(int)bullet[i].getZ())>6400*3) 
                removeBullet(i);
        }

        //code for meteors
        shipColision();
        int bounds=6400*3;

        for(int i=0;i<meteors;i++){
            // Change Meteor position if it is out of bounds
            if (meteor[i].getX()-x>bounds)
                meteor[i].changeX(-(bounds*2-100));
            else if (meteor[i].getX()-x<-bounds)
                meteor[i].changeX(bounds*2-100);
            if (meteor[i].getZ()-z>bounds)
                meteor[i].changeZ(-(bounds*2-100));
            else if (meteor[i].getZ()-z<-bounds)
                meteor[i].changeZ(bounds*2-100);
            if (meteor[i].getY()-y>bounds)
                meteor[i].changeY(-(bounds*2-100));
            else if (meteor[i].getY()-y<-bounds)
                meteor[i].changeY(bounds*2-100);
            for(int j=0;j<bullets;j++){
                if(u.distance(meteor[i].getX(),meteor[i].getY(),
                    meteor[i].getZ(),bullet[j].getX(),bullet[j].getY(),
                    bullet[j].getZ())<meteor[i].getSize()+30){
                    //double dirX=r.nextDouble()*Math.PI*2;
                    //double dirY=r.nextDouble()*Math.PI*2;
                    //try{
                    //int R=meteor[i].getSize()/2;
                    score+=5;
                    newExplosion(meteor[i].getX(),meteor[i].getY(),
                        meteor[i].getZ(),meteor[i].getSize());
                    //     }catch(Exception e){}
                    if (split){
                        newMeteor(meteor[i].getX(),meteor[i].getY(),
                            meteor[i].getZ(),meteor[i].getSize()/2);
                        meteor[meteors-1].setSpeed(10);
                        if(meteor[meteors-1].getSize()<200)
                            removeMeteor(meteors-1);

                        newMeteor(meteor[i].getX(),meteor[i].getY(),
                            meteor[i].getZ(),meteor[i].getSize()/2);
                        meteor[meteors-1].setSpeed(10);
                        //deletes meteors if they get too small
                        if(meteor[meteors-1].getSize()<200)
                            removeMeteor(meteors-1);

                    }
                    removeBullet(j);
                    removeMeteor(i);
                }
            }
        }

        ufoAI();

        if(ufos==0){
            spawnUfos(5);
        }

        for(int i=0;i<particles;i++){
            if (c%10==0) particle[i].changeRadius(-1);
        }
        c++;
        if(particles>0)
            if(particle[0].getRadius()<=0) removePartical(0);

        fillAll(g);
        g.setColor(Color.white);
        if(freeLook==0){
            g.fillOval(300-1,300-1,2,2);
            g.drawOval(300-5,300-5,10,10);
        }
        
        
        //Draws Health Bar
        g.setColor(Color.red);
        g.fillRect(10,580-30,(int)((Health/100)*580),15);
        //Draws Score
        g.setColor(Color.white);
        g.drawString(Integer.toString(score),10,10);
        
        
        timer.start();
    }

    public void spawnUfos(int amount){
        double changeAngle=Math.PI*2/amount;
        double angle=0;
        int distance=6400*4;
        for(int i=0;i<amount;i++){

            newUfo(centerX+(int)(Math.cos(angle)*distance),centerY,
                centerZ+(int)(Math.sin(angle)*distance),800);
            angle+=changeAngle;
        }
    }

    int bulletTimer=0;
    int bulletCoolDown=100;
    public void ufoAI(){
        bulletTimer++;
        for(int i=0;i<ufos;i++){
            double distance=
                u.distance(ufo[i].getX(),ufo[i].getY(),ufo[i].getZ(),
                    centerX,centerY,centerZ);
            //If ufo is far away it will go straight towards the player.
            if(distance>6400*2){
                int changeX=ufo[i].getX()-centerX;
                int changeY=ufo[i].getY()-centerY;
                int changeZ=ufo[i].getZ()-centerZ;
                double XZDistance=Math.sqrt(changeX*changeX+changeZ*changeZ);
                //System.out.println("AH");
                ufo[i].setSpeed(60);
                if (distance>6400*3) ufo[i].setSpeed(180);
                double dirX;
                if(changeX!=0)
                   dirX=Math.atan(changeZ/changeX);
                else dirX=1000000;
                if(changeX<0)dirX+=Math.PI;
                ufo[i].setDirectionX(dirX);
  
                double dirY=Math.atan(changeY/XZDistance)+Math.PI;
                //if(XZDistance>0) dirY+=Math.PI;
                ufo[i].setDirectionY(dirY);

            }
            else{
                //if in range it will start to change flight path randomly
                //Shoot Bullets
                if((bulletTimer+(int)(bulletCoolDown/(i+1)))
                %bulletCoolDown==0){
                    int changeX=ufo[i].getX()-centerX;
                    int changeY=ufo[i].getY()-centerY;
                    int changeZ=ufo[i].getZ()-centerZ;
                    double XZDistance=Math.sqrt(changeX*changeX+changeZ*changeZ);
                    
                    double dirX=Math.atan(changeZ/changeX);
                    if(changeX<0)dirX+=Math.PI;
                    
                    double dirY=Math.atan(changeY/XZDistance)+Math.PI;
                    
                    newBullet(ufo[i].getX(),ufo[i].getY(),ufo[i].getZ()
                    ,8*2,dirX,dirY,100,Color.red);
                }
                
                ufo[i].setSpeed(25);//+(i/ufos)*30);
                ufo[i].addDirectionX(ufo[i].random()
                    *Math.PI/3000-Math.PI/1500);
                ufo[i].addDirectionY(ufo[i].random()
                    *Math.PI/3000-Math.PI/1500);
                //System.out.println(Math.random());
            }

            //Prevent Clumping
            for(int j=0;j<ufos;j++){
                int changeX=ufo[i].getX()-ufo[j].getX();
                //int changeY=ufo[i].getY()-ufo[j].getY();
                int changeZ=ufo[i].getZ()-ufo[j].getZ();
                double XZDistance=Math.sqrt(changeX*changeX+changeZ*changeZ);
                if(u.distance(ufo[i].getX(),ufo[i].getY(),ufo[i].getZ(),
                    ufo[j].getX(),ufo[j].getY(),ufo[j].getZ())<1600){
                    if(changeX==0)changeX=1;
                    double dirX=Math.atan(changeZ/changeX);
                    if(changeX<0)dirX+=Math.PI;
                    dirX+=r.nextDouble()/1000-1/2000;
                    ufo[i].changeX((int)(Math.cos(dirX)*10));
                    ufo[i].changeZ((int)(Math.sin(dirX)*10));
                    ufo[j].changeX((int)(Math.cos(dirX+Math.PI)*10));
                    ufo[j].changeZ((int)(Math.sin(dirX+Math.PI)*10));
                    if(distance<6400*2){
                        //ufo[i].addDirectionX(r.nextDouble()
                        //*Math.PI/2-Math.PI/4);
                    }
                }
            }

            for(int j=0;j<bullets;j++){
                if(u.distance(ufo[i].getX(),ufo[i].getY(),
                    ufo[i].getZ(),bullet[j].getX(),bullet[j].getY(),
                    bullet[j].getZ())<meteor[i].getSize()+30
                    &&bullet[j].getColor()==Color.orange){
                    newExplosion(ufo[i].getX(),ufo[i].getY(),
                        ufo[i].getZ(),meteor[i].getSize());
                    removeBullet(j);
                    removeUfo(i);
                    score+=10;
                }
            }
        }
    }
    // public double trueRandom(int h){
    // double random=Math.random();
    // for(int i=0;i<h;i++){
    // random=Math.random();
    // }
    // return random;
    // }
    int centerX;
    int centerY;
    int centerZ;
    public void shipColision(){
        for(int i=0;i<meteors;i++){
            if(u.distance(meteor[i].getX(),meteor[i].getY(),
                meteor[i].getZ(),centerX,centerY,
                centerZ)<meteor[i].getSize()+330){
                //System.out.println("It Worked");
                newExplosion(meteor[i].getX(),meteor[i].getY(),
                    meteor[i].getZ(),meteor[i].getSize());
                Health-=20;
                removeMeteor(i);
            }
        }

        for(int j=0;j<bullets;j++){
            if(u.distance(centerX,centerY,centerZ
            ,bullet[j].getX(),bullet[j].getY(),bullet[j].getZ())<330 
                && bullet[j].getColor()==Color.red){
                removeBullet(j);
                Health-=10;
            }
        }

        //return false;
    }

    public void newMeteor(int x, int y,int z, int size){
        meteor[meteors]=new Meteor(x,y,z,size);
        meteors++;
    }

    public void newParticle(int x,int y,int z,int r,Color c){
        particle[particles]=new Ball(x, y, z, r, c);
        particles++;
    }

    public void newBullet(int x,int y,int z,int r,double dirX,double dirY,int speed,Color c){
        if(bullets==bullet.length) removeBullet(0);
        bullet[bullets]=new Ball(x, y, z, r, c);
        //bulletDirection[bullets]=dirX;//-1*(rotation)-Math.PI/2;
        bullet[bullets].setDirectionX(dirX);
        bullet[bullets].setDirectionY(dirY);
        bullet[bullets].setSpeed(speed);
        //if(c==Color.red)
        //bullet[bullets].setBulletSpeed(100);
        bullets++;
    }

    public void newExplosion(int x,int y,int z,int r){
        //throws IOException{
        try{

            if(explosions==explosion.length) removeExplosion(0);
            explosion[explosions]=new ExplosionGif(x, y, z, r);
            explosions++;
        }catch(Exception e){}

    }

    public void newUfo(int x,int y,int z,int size){
        ufo[ufos]=new UFO(x, y, z, size);
        ufos++;
    }

    public void fillAll(Graphics g){
        u.setRotationX(rotation+freeLook);
        u.setTranslation(-x,-y,-z);
        int length=meteors+particles+1+bullets+explosions+ufos;
        //the 1 is the ship
        int[] distance = new int[length];
        for(int i=0;i<length;i++){
            distance[i]=camDistance(i);
        }
        int[] order=u.arrayOrder(distance);
        int d;

        for(int i=length-1;i>=0;i--){
            d=order[i];
            fill(d,g);
        }

    }

    public int camDistance(int i){
        if (i<meteors){
            return (int)u.camDistance(meteor[i].getX(),
                meteor[i].getY(), meteor[i].getZ());
        } else if (i<meteors+particles){
            i-=meteors;
            return (int)u.camDistance((int)particle[i].getX(),
                (int)particle[i].getY(), (int)particle[i].getZ());
        } else if (i<meteors+particles+1){
            //the ship
            return 150*2;
            //260,360,300
        } else if (i<meteors+particles+1+bullets){
            i-=meteors+particles+1;
            return (int)u.camDistance((int)bullet[i].getX(),
                (int)bullet[i].getY(), (int)bullet[i].getZ());
        }else if (i<meteors+particles+1+bullets+explosions){
            i-=meteors+particles+1+bullets;
            return (int)u.camDistance((int)explosion[i].getX(),
                (int)explosion[i].getY(), (int)explosion[i].getZ());
        }else if (i<meteors+particles+1+bullets+explosions+ufos){
            i-=meteors+particles+1+bullets+explosions;
            return (int)u.camDistance(ufo[i].getX(),
                ufo[i].getY(), ufo[i].getZ());
        }

        return 0;
    }

    public void fill(int i,Graphics g){
        //i is what one is filled
        if (i<meteors){
            meteor[i].setRotation(rotation+freeLook);
            meteor[i].setRotationY(rotationY);
            meteor[i].setTranslation(-x,-y,-z);
            meteor[i].setZoom(zoom);
            meteor[i].fill(g);
        } else if (i<meteors+particles){
            if(freeLook!=0){
                i-=meteors;
                particle[i].setRotation(rotation+freeLook);
                particle[i].setRotationY(rotationY);
                particle[i].setTranslation(-x,-y,-z);
                particle[i].fill(g);
            }
        } else if (i<meteors+particles+1){
            ship.setZoom(zoom);
            ship.setFreeLook(freeLook);
            ship.fill(g);
        } else if (i<meteors+particles+1+bullets){
            i-=meteors+particles+1;
            bullet[i].setRotation(rotation+freeLook);
            bullet[i].setTranslation(-x,-y,-z);
            bullet[i].fill(g);
        }else if (i<meteors+particles+1+bullets+explosions){
            i-=meteors+particles+1+bullets;
            explosion[i].setRotation(rotation+freeLook);
            explosion[i].setTranslation(-x,-y,-z);
            explosion[i].fill(g);
        }else if (i<meteors+particles+1+bullets+explosions+ufos){
            i-=meteors+particles+1+bullets+explosions;
            ufo[i].setRotation(rotation+freeLook);
            ufo[i].setTranslation(-x,-y,-z);
            ufo[i].fill(g);
        }
    }

    public void removePartical(int remove){
        for(int i=remove;i<particles-1;i++){
            particle[i]=particle[i+1];
        }
        particles--;
    }

    public void removeBullet(int remove){
        for(int i=remove;i<bullets-1;i++){
            bullet[i]=bullet[i+1];
        }
        bullets--;
    }

    public void removeMeteor(int remove){
        for(int i=remove;i<meteors-1;i++){
            meteor[i]=meteor[i+1];
        }
        meteors--;
    }

    public void removeExplosion(int remove){
        for(int i=remove;i<explosions-1;i++){
            explosion[i]=explosion[i+1];
        }
        explosions--;
    }

    public void removeUfo(int remove){
        for(int i=remove;i<ufos-1;i++){
            ufo[i]=ufo[i+1];
        }
        ufos--;
    }

    private class MoveListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(Health>0)
            repaint();
        }
    }

    boolean keyW=false; boolean keyS=false; 
    boolean keyA=false; boolean keyD=false; 
    boolean keySpace=false; boolean keyShift=false; 
    boolean keyQ=false; boolean keyE=false;
    public void keyTyped(KeyEvent keyt) {}

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A : keyA = true; break;
            case KeyEvent.VK_D : keyD = true; break;
            case KeyEvent.VK_W : keyW = true; break;
            case KeyEvent.VK_S : keyS = true; break;
            case KeyEvent.VK_B : freeLook = Math.PI; break;
            case KeyEvent.VK_SPACE : keySpace = true; break;
            case KeyEvent.VK_SHIFT : keyShift = true; break;
            case KeyEvent.VK_Q : keyQ = true; break;
            case KeyEvent.VK_E : keyE = true; break;
            case KeyEvent.VK_UP : newBullet((int)(x+300-(140)*
                    Math.sin(rotation-ship.getRotation()-Math.PI/2))
            ,(int)y+410,(int)(z+300-(140)*
                    Math.cos(rotation-ship.getRotation()-Math.PI/2))
            ,8*2,-1*(rotation)-Math.PI/2,0,500,Color.orange); break;
        }

    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A : keyA = false; break;
            case KeyEvent.VK_D : keyD = false; break;
            case KeyEvent.VK_W : keyW = false; break;
            case KeyEvent.VK_S : keyS = false; break;
            case KeyEvent.VK_B : freeLook = 0; break;
            case KeyEvent.VK_SPACE : keySpace = false; break;
            case KeyEvent.VK_SHIFT : keyShift = false; break;
            case KeyEvent.VK_Q : keyQ = false; break;
            case KeyEvent.VK_E : keyE = false; break;
        }

    }
}
