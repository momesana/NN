
import scala.util.Random
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.control._
import scalafx.scene.layout.BorderPane
import scalafx.scene.paint.Color

object Visualizer extends JFXApp {

  val random = new Random(System.currentTimeMillis)

  def createV2D(components: Double*) = new Vector(Seq(components: _ *))

  def learningSamples: Seq[(Vector, Int)] = {
    Seq(-0.2, 0.7, 0.3, 0.6, 0.6, 0.6, -0.9, 0.3, -0.4, -0.4, -0.9, -0.4, 1.0, -0.4, 1.0, -0.7, 0.7, -0.9)
      .grouped(2)
      .map(createV2D)
      .grouped(3)
      .zipWithIndex
      .flatMap {
        case (vectors: Seq[Vector], label: Int) => vectors.map((_, label))
      }.toSeq
  }

  lazy val dc = new DigitClassifier(2, 3, 0.95, true, 100)

  val canvas = new Canvas(600, 600)

  val coordinateSystem = new CoordinateSystem((-1, 1), (-1, 1), canvas)

  def drawInputVectors(): Unit = {
    val colors = Seq(Color.Green, Color.Blue, Color.Red)
    learningSamples.foreach { case (vector: Vector, label: Int) => coordinateSystem.drawVector2D(colors(label), vector) }
  }

  def takeSample: (Vector,Int) = learningSamples(random.nextInt(learningSamples.length))

  def drawWeightVectors(): Unit = dc.weights.zipWithIndex.foreach { case (w, i) => coordinateSystem.drawWeight(s"w$i", w) }

  def updateCanvas(): Unit = {
    coordinateSystem.clear()
    drawInputVectors()
    drawWeightVectors()
  }

  def trainWithRandomInput(ae: ActionEvent): Unit = {
    dc.train(takeSample._1)
    updateCanvas()
  }

  def trainABunchOfTimes(ae: ActionEvent): Unit = {
    (0 until 100).map(_ => takeSample).foreach { case (v,_) => dc.train(v) }
    updateCanvas()
  }

  def validateClassifications(ae: ActionEvent): Unit = {
    val mapping = dc.getLabelClusterMapping(learningSamples)
    val correct: Int = learningSamples.count {
      case (vector: Vector, label: Int) => {
        println(s"classified as ${mapping(dc.classify(vector))} while the sample is labeled as $label")
        mapping(dc.classify(vector)) == label
      }
    }
    val total = learningSamples.length
    println(s"correct: $correct, wrong: ${total - correct}, success rate: ${(correct.toDouble/total*100).toInt}%")
  }

  stage = new JFXApp.PrimaryStage {
    title = "Digitizer GUI"
    scene = new Scene(800, 600) {

      val toolBar = new ToolBar
      val nextButton = new Button("Train with Random Sample")
      val testButton = new Button("Validate Classifications")
      val test100Button = new Button("Test 100 Times with Random Input")

      nextButton.onAction = trainWithRandomInput _
      testButton.onAction = validateClassifications _
      test100Button.onAction = trainABunchOfTimes _
      toolBar.items = List(nextButton, testButton, test100Button)

      drawInputVectors()
      drawWeightVectors()

      val rootPane = new BorderPane
      rootPane.top = toolBar
      rootPane.center = canvas
      root = rootPane
    }
  }
}
