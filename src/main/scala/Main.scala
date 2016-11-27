
import scala.util.Random

object Main {

  val random: Random = new Random(System.currentTimeMillis)

  def takeSample(samples: Seq[(Vector, Int)]): (Vector,Int) = samples(random.nextInt(samples.length))

  def printUsageAndExit(): Unit = {
    println("usage: digitclassifier <trainingdata>.trn <testdata>.tst")
    sys.exit(1)
  }

  def main(args: Array[String]) {
    if (args.length != 2) printUsageAndExit()

    val parser = new InputParser
    val trainingDataPath = args(0)
    val testDataPath = args(1)

    val trainingData = parser.parseInput(trainingDataPath)
    val testData = parser.parseInput(testDataPath)

    val dimensions = 192
    val clusterCount = 10
    val iterations = 5000
    val learningRate = 0.995
    val digitClassifier = new DigitClassifier(dimensions, clusterCount, learningRate, false, iterations)

    // train the perceptron
    (0 until iterations).map(_ => takeSample(trainingData)).foreach {
      case (vector: Vector, _) => digitClassifier.train(vector)
    }

    // get mapping of clusters to labels
    val mapping = digitClassifier.getLabelClusterMapping(trainingData)

    // now check how good it works
    val correct: Int = testData.count {
      case (vector: Vector, label: Int) => mapping.get(digitClassifier.classify(vector)) match {
        case (classification: Some[Int]) => classification.get == label
        case _ => false
      }
    }
    
    val total = testData.length
    println(s"correct: $correct, wrong: ${total - correct}, success rate: ${(correct.toDouble/total*100).toInt}%")
  }
}
