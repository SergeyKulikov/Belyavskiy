package info.kulikov.belyavskiy

import java.lang.StringBuilder
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

class Algorithm {
    private val arrPoints: MutableList<Point> = mutableListOf()

    fun start(foldInHalfValue: Int) : MutableList<Point> {

        // Функция получает на вход количество сгибаний ленты и формирует
        // точки координат на плоскости, которые пишем в arrPoints
        // Точки необходимо соедениеть последовательно от 0 до последнего алемента
        // arrPoints. tmpArr используется как промежуточный массив для сортировки и
        // поворота точек плоскости.

        // Можно было бы обойтись одним массивом, но это усложнит понимание кода

        // Функция не ограничена по количество сгибаний, но с учетом геометрической
        // програессии, даже на 20 сгибах считать это будет... долго

        val tmpArr: MutableList<Point> = mutableListOf()

        // Начальный отрезок
        arrPoints.clear()
        arrPoints.add(Point(0.0, 0.0))
        arrPoints.add(Point(0.0, 1.0))

        // А теперь сгибаем ленту
        for (j in 1..foldInHalfValue) {
            // Последняя точка не копируется, но она явлется центром,
            // Вокруг которого идет поворот точек
            tmpArr.clear()
            for (i in arrPoints.size - 2 downTo 0 step 1) {
                tmpArr.add(Point(arrPoints[i].x, arrPoints[i].y))
            }

            rotatePoints(90.0, tmpArr, arrPoints[arrPoints.size - 1])

            // Повернутую фигуру переносим в основной массив
            for (i in 0 until tmpArr.size) {
                arrPoints.add(Point(tmpArr[i].x, tmpArr[i].y))
            }
        }

        printPoints("$foldInHalfValue", arrPoints)

        return arrPoints;
    }

    private fun rotatePoints(angle: Double, arr: MutableList<Point>, center: Point): MutableList<Point> {
        val cs = cos(Math.toRadians(angle))
        val sn = sin(Math.toRadians(angle))

        var dx: Double
        var dy: Double

        var j: Int = arr.size
        while (j-- > 0) {
            dx = arr[j].x - center.x
            dy = arr[j].y - center.y

            arr[j].x = (dx * cs - sn * dy + center.x).roundToInt().toDouble()
            arr[j].y = (dx * sn + cs * dy + center.y).roundToInt().toDouble()
        }
        return arr;
    }

    override fun toString() : String {
        val stringBuilder = StringBuilder()

        stringBuilder.clear()
        stringBuilder.append("Точек ${arrPoints.size} = ")
        for (i in 0 until arrPoints.size) {
            if (i > 0) { stringBuilder.append(" -> ") }
            stringBuilder.append ("[${arrPoints[i].x.toInt()}; ${arrPoints[i].y.toInt()}]")
        }

        return stringBuilder.toString()
    }

    private fun printPoints(text: String, arr: MutableList<Point>) {
        for (item in arr) {
            println("$text == [${item.x}; ${item.y}]")
        }
    }


}
