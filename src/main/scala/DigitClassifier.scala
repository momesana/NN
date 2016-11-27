
import scala.util.Random

class DigitClassifier(val dimensions: Int,
                      val clusterCount: Int,
                      val learningRate: Double = 1,
                      val differenceUpdate: Boolean = true,
                      val iterations: Int = 50000) {

  private val random = new Random(System.currentTimeMillis)
  val numOfIterations = 50000
  val numOfClusters = 10
  var weights: Seq[Vector] = randomInit()
  var learningRates: Seq[Double] = List.fill(clusterCount)(1)

  private def randomVector(): Vector =
    new Vector((0 until dimensions).map(_ => random.nextInt(numOfClusters + 1).toDouble / 10.0))

  private def randomSample(samples: Seq[(Vector, Int)]): Vector = samples(random.nextInt(samples.length))._1

  private def randomInit(): Seq[Vector] =
    (0 until clusterCount).map(_ => randomVector()).map(_.normalized)

  def getLabelClusterMapping(samples: Seq[(Vector, Int)]): Map[Int,Int] = {
    samples.groupBy(_._2).map {
      case (label: Int, samples: Seq[(Vector, Int)]) => mostFrequent(samples.map(_._1).map(classify)) -> label
    }
  }

  def mostFrequent(classifications: Seq[Int]): Int =
    classifications.groupBy(identity).maxBy(_._2.length)._1

  def train(sample: Vector): Unit = updateWeight(sample.normalized, nearestCluster(sample.normalized))

  def train(samples: Seq[(Vector, Int)], iterations: Int = iterations): Unit =
    (0 until iterations).map(_ => randomSample(samples)).foreach(train)

  def classify(sample: Vector): Int = nearestCluster(sample)

  private def nearestCluster(sample: Vector): Int =
    weights.map(weight => weight.dotProduct(sample)).zipWithIndex.maxBy(_._1)._2

  private def updateWeight(x: Vector, index: Int) {
    val w = weights(index)
    val lr = learningRates(index) * learningRate
    val diff = if (differenceUpdate) (x - w) * lr else x * lr
    learningRates = learningRates.patch(index, Seq(lr), 1)
    weights = weights.patch(index, Seq((w+diff).normalized), 1)
  }
}
