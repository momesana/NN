import scalafx.geometry.Point2D
import scalafx.scene.canvas.Canvas
import scalafx.scene.paint.Color

class CoordinateSystem(val xrange: (Double, Double), val yrange: (Double, Double), var canvas: Canvas) {

  private lazy val gc = canvas.graphicsContext2D

  val pos: Point2D = new Point2D(8.4,8.8)

  private def centerPoint(): Point2D = new Point2D(canvas.width.toDouble / 2.0, canvas.height.toDouble / 2.0)

  def drawAxes(): Unit = {
    val center: Point2D = centerPoint()
    gc.save()
    gc.setStroke(Color.Black)
    gc.strokeLine(0, center.y, canvas.width.toDouble, center.y)
    gc.strokeLine(center.x, 0, center.x, canvas.height.toDouble)
    gc.restore()
  }

  private def strokeLine(p1: Point2D, p2: Point2D): Unit = {
    gc.strokeLine(p1.x, p1.y, p2.x, p2.y)
  }

  def drawWeight(label: String, vector: Vector): Unit = {
    gc.save()
    gc.setLineDashes(1, 3, 7, 12)
    drawVector2D(Color.Gray, vector)
    val arrowPos = canvasCoords(vector.components(0), vector.components(1))
    gc.fillText(label, arrowPos.x, arrowPos.y)
    gc.restore()
  }

  def drawVector2D(color: Color, vector: Vector): Unit = {
    gc.setStroke(color)
    val components = vector.components
    val x = components(0)
    val y = components(1)

    strokeLine(canvasCoords(0,0), canvasCoords(x,y))
  }

  private def canvasCoords(x: Double, y: Double): Point2D = canvasCoords(new Point2D(x, y))

  private def canvasCoords(point: Point2D): Point2D = {
    val center = centerPoint()
    val xfactor = canvas.width.toDouble / (Math.abs(xrange._1) + Math.abs(xrange._2))
    val yfactor = -canvas.height.toDouble / (Math.abs(yrange._1) + Math.abs(yrange._2))

    new Point2D(point.x * xfactor + center.x, point.y * yfactor + center.y)
  }

  def clear(): Unit = {
    gc.clearRect(0, 0, canvas.width.toDouble, canvas.height.toDouble)
    drawAxes()
  }

  drawAxes()
}
