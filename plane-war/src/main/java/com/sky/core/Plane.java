package com.sky.core;

import com.sky.client.PlaneWarClient;
import com.sky.constant.Constant;
import com.sky.util.FileUtil;
import com.sky.util.ImageUtil;
import com.sky.util.MusicUtil;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.ForkJoinPool;

@NoArgsConstructor
public class Plane extends PlaneWarObject {

    public double speed = 10;// 速度
    public boolean left, up, right, down; // 方向
    public int blood;// 血量
    public int level;// 等级
    public int type;// 等级
    public int score = 0;// 积分
    public boolean live = true; // 是否存活
    public int superFireCount = 3; // 大招剩余次数
    public boolean fire; // 是否开火
    private boolean fireMusicIsPlay; // 开火音乐是否播放结束

    public Plane(PlaneWarClient pwc, boolean good) {
        this.fire = false;
        this.x = (Constant.GAME_WIDTH - width) / 2;
        this.y = Constant.GAME_HEIGHT - height;
        this.img = ImageUtil.get("myPlane_01_01");
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.pwc = pwc;
        this.good = good;
        this.blood = Constant.PLANE_MAX_BLOOD;
        this.level = 1;
        this.type = 1;
    }

    public Plane(int x, int y, Image img, int width, int height) {
        super();
        this.x = x;
        this.y = y;
        this.img = img;
        this.width = width;
        this.height = height;
    }

    public Plane(int x, int y, String imageName) {
        super();
        this.x = x;
        this.y = y;
        this.img = ImageUtil.get(imageName);
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    public Plane(int x, int y, Image img) {
        super();
        this.x = x;
        this.y = y;
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    /**
     * 判断我方飞机出界问题
     */
    private void outOfBounds() {
        if (x <= 0)
            x = 0;
        if (x >= (Constant.GAME_WIDTH - width))
            x = Constant.GAME_WIDTH - width;
        if (y <= 0)
            y = 0;
        if (y >= (Constant.GAME_HEIGHT - height))
            y = Constant.GAME_HEIGHT - height;
    }

    /**
     * 我方飞机发子弹的方法
     */
    public void fire() {
        Missile missile = new Missile(pwc, this.x, this.y, "myPlane_missile_0" + type + "_0" + level, good);
        missile.x += (this.width - missile.width) / 2;
        missile.y -= height;
        pwc.missiles.add(missile);
        ForkJoinPool.commonPool().execute(() -> {
            if (!fireMusicIsPlay) {
                fireMusicIsPlay = true;
                MusicUtil.play(FileUtil.getFilePath(Constant.MUSIC_PRE + "fire3.mp3"));
                fireMusicIsPlay = false;
            }
        });
    }

    /**
     * 超级子弹
     */
    public void superFire() {
        if (superFireCount > 0) {
            int num = 24;
            for (int i = 1; i <= num; i++) {
                Missile missile = new Missile(pwc, this.x, this.y, "myPlane_missile_super", 6, good);
                int r = (int) (Math.sqrt(width * width + height * height) / 2);
                int theta = 360 * i / num;
                missile.setTheta(theta);
                missile.x = (int) (missile.x + (width / 2 + r * Math.sin(Math.toRadians(theta)) - missile.width / 2));
                missile.y = (int) (missile.y
                        - ((r * Math.cos(Math.toRadians(theta)) - height / 2 + missile.height / 2)));
                pwc.missiles.add(missile);
            }
            superFireCount--;
        }
    }


    @Override
    public void move() {
        if (left) {
            x -= speed;
        }
        if (right) {
            x += speed;
        }
        if (up) {
            y -= speed;
        }
        if (down) {
            y += speed;
        }
        outOfBounds();
        if (fire) {
            fire();
        }
    }

    @Override
    public void draw(Graphics g) {
        img = ImageUtil.get("myPlane_0" + type + "_0" + level);
        if (blood <= 0 && live) {
            live = false;
            Explode ex = new Explode(pwc, x, y);
            ex.x += (width - ex.width) / 2;
            ex.y += (height - ex.height) / 2;
            pwc.explodes.add(ex);
            pwc.enemyPlanes.clear();
            pwc.missiles.clear();
            pwc.items.clear();

        }

        if (live) {
            g.drawImage(img, x, y, null);
            move();
        }
        drawBloodAndScore(g);
    }

    /**
     * 画血条和积分
     *
     * @param g
     */
    private void drawBloodAndScore(Graphics g) {
        Image bloodImg = ImageUtil.get("myBlood");
        Image blood_blank = ImageUtil.get("myBlood_blank");
        Image scoreImg = ImageUtil.get("score");
        int i = 0;
        g.drawImage(bloodImg, 10, 40, null);
        int num = (int) (((double) ((bloodImg.getWidth(null)) - 56) / (Constant.PLANE_MAX_BLOOD))
                * (Constant.PLANE_MAX_BLOOD - blood) / blood_blank.getWidth(null));
        for (int j = 0; j < num; j++) {
            g.drawImage(blood_blank, 10 + bloodImg.getWidth(null) - blood_blank.getWidth(null) * (j + 1), 40 + 14,
                    null);
        }
        // 画积分
        g.drawImage(ImageUtil.get("score"), 10, 40 + bloodImg.getHeight(null) + 12, null);
        g.setFont(new Font("微软雅黑", Font.BOLD, 40));
        g.setColor(Color.WHITE);
        g.drawString(score + "", 10 + scoreImg.getWidth(null) + 10, 40 + bloodImg.getHeight(null) + 50);
    }

    /**
     * 按下键盘的方法
     *
     * @param e 键盘事件
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_J:// 发子弹
                fire = true;
                break;
            case KeyEvent.VK_SPACE:// 发超级子弹
                superFire();
                break;
        }
    }

    /**
     * 松开键盘的方法
     *
     * @param e 键盘事件
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_J:// 发子弹
                fire = false;
                break;
        }
    }
}
