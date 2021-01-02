package po

import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.*
import java.util.concurrent.TimeUnit
import javax.swing.ImageIcon
import javax.swing.JFrame

class CarGame : JFrame("Racing Game"), KeyListener, ActionListener {
    var roadmove = 0
    var rand = Random()
    private var usercar: ImageIcon? = null
    private var ai: ImageIcon? = null
    private var ai2: ImageIcon? = null
    private var ai3: ImageIcon? = null
    private var bush: ImageIcon? = null
    private var bush2: ImageIcon? = null
    private var boom: ImageIcon? = null
    var randomNum = Random()
    private val result = randomNum.nextInt(4)
    var xpos = 250
    var ypos = 500
    var scoreCounter = 0
    var carxpos = intArrayOf(100, 250, 400, 550)
    var carypos = intArrayOf(100, 250, 400, 550)
    var cxpos1 = 0
    var cxpos2 = 2
    var cxpos3 = 3
    var bushPos = 0
    var cypos1 = rand.nextInt(3)
    var cypos2 = rand.nextInt(3)
    var cypos3 = rand.nextInt(3)
    var y1pos = (Math.random() * 100).toInt()
    var y2pos = (Math.random() * 100).toInt()
    var y3pos = (Math.random() * 100).toInt()
    var isGameOver = false
    var paint = false
    override fun paint(g: Graphics) {
        g.color = Color.GREEN
        g.fillRect(0, 0, 700, 700)
        g.color = Color.GRAY
        g.fillRect(90, 0, 10, 700)
        g.fillRect(600, 0, 10, 700)
        g.color = Color.BLACK
        g.fillRect(100, 0, 500, 700)
        var i = 0
        while (i < 700) {
            g.color = Color.WHITE
            g.fillRect(350, i, 10, 70)
            i += 100
        }
        bushPos -= 50
        if (bushPos <= -700) {
            bushPos = 0
        }
        //paint the car
        if (roadmove == 0) {
            var i = 0
            while (i <= 700) {
                g.color = Color.WHITE
                g.fillRect(350, i, 10, 70)
                i += 100
            }
            roadmove = 1
        } else if (roadmove == 1) {
            run {
                var i = 0
                while (i <= 700) {
                    g.color = Color.BLACK
                    g.fillRect(350, i, 10, 70)
                    i += 100
                }
            }
            var i = 50
            while (i <= 700) {
                g.color = Color.WHITE
                g.fillRect(350, i, 10, 70)
                i += 100
            }
            roadmove = 0
        }


        //g.drawString(Integer.toString(scoreCounter), 20, 600);
        g.font = Font("ComicSans", Font.BOLD, 60)
        g.drawRoundRect(315, 40, 80, 60, 5, 5)
        g.color = Color.darkGray
        g.fillRoundRect(315, 40, 80, 60, 5, 5)
        g.color = Color.lightGray
        g.drawString(Integer.toString(scoreCounter), 315, 90)
        usercar = ImageIcon("images-2/car_left_2.png")
        usercar!!.paintIcon(this, g, xpos, ypos)
        ai = ImageIcon("images-2/car_left_3.png")
        ai!!.paintIcon(this, g, carxpos[cxpos1], y1pos)
        ai2 = ImageIcon("images-2/car_right_1.png")
        ai2!!.paintIcon(this, g, carxpos[cxpos2], y2pos)
        ai3 = ImageIcon("images-2/car_left_1.png")
        ai3!!.paintIcon(this, g, carxpos[cxpos3], y3pos)
        bush = ImageIcon("images-2/bush.png")
        bush!!.paintIcon(this, g, 0, bushPos)
        bush2 = ImageIcon("images-2/bush.png")
        bush2!!.paintIcon(this, g, 625, bushPos)
        y1pos += 200
        if (y1pos > 700) {
            cxpos1 = randomNum.nextInt(4)
            cypos1 = randomNum.nextInt(4)
            y1pos = 0
            scoreCounter++
        }
        y2pos += 200
        if (y2pos > 700) {
            cxpos2 = randomNum.nextInt(4)
            cypos2 = randomNum.nextInt(4)
            y2pos = 100
            scoreCounter++
        }
        y3pos += 200
        if (y3pos > 700) {
            cxpos3 = randomNum.nextInt(4)
            cypos3 = randomNum.nextInt(4)
            y3pos = 50
            scoreCounter++
        }
        checkForCollision(g)
        try {
            Thread.sleep(500)
            TimeUnit.MILLISECONDS.sleep(3)
        } catch (e: InterruptedException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        //repaint();
    }

    /**
     *
     */
    fun checkForCollision(g: Graphics) {
        if (y1pos < ypos && y1pos + 125 -200> ypos + 40 && carxpos[cxpos1] == xpos ||
            y2pos < ypos && y2pos + 125 -200> ypos + 40 && carxpos[cxpos2] == xpos ||
            y3pos < ypos && y3pos + 125 > ypos + 40 && carxpos[cxpos3] == xpos
        ) {
            isGameOver = true
            boom = ImageIcon("images-2/boom.png")
            boom!!.paintIcon(this, g, xpos - 60, ypos - 100)
            g.color = Color.WHITE
            g.drawString("Game Over", 200, 200)
        }
        if (isGameOver) {
            if (!paint) {
                repaint()
                paint = true
            }
        } else {
            repaint()
        }
    }

    override fun keyPressed(e: KeyEvent) {
        if (e.keyCode == KeyEvent.VK_LEFT) {
            xpos = xpos - 150
            println(xpos)
            println(y1pos)
            if (xpos < 100) {
                xpos = 100
            }
        }
        if (e.keyCode == KeyEvent.VK_RIGHT) {
            xpos = xpos + 150
            println(xpos)
            if (xpos > 550) {
                xpos = 550
            }
        }
    }

    override fun actionPerformed(e: ActionEvent) {
        // TODO Auto-generated method stub
    }

    override fun keyTyped(e: KeyEvent) {
        // TODO Auto-generated method stub
    }

    override fun keyReleased(e: KeyEvent) {
        // TODO Auto-generated method stub
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val m = CarGame()
        }
    }

    init {
        setBounds(300, 10, 700, 700)
        isVisible = true
        addKeyListener(this)
        defaultCloseOperation = EXIT_ON_CLOSE
        //int cypos1 = rand.nextInt(3), cypos2 = rand.nextInt(3), cypos3 = rand.nextInt(3);
    }
}