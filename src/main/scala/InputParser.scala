
import scala.io.Source

class InputParser {

  private def extractLabel(rawLabel: Seq[Double]): Int = {
    if (rawLabel.length != 10)
      throw new RuntimeException("the line with the label should have exactly 10 entries")

    rawLabel.zipWithIndex.maxBy(_._1) match {
      case (label: Double, index: Int) =>
        if (label == 0.0)
          throw new RuntimeException("no label was found")
        else if (label != 1.0)
          throw new RuntimeException("label has bad value")
        else index
      case _ => throw new RuntimeException("couldn't match label")
    }
  }

  def parseLabeledEntry(rawContent: String): Option[(Vector,Int)] = {
    try {
      val groupedValues = rawContent.split("\\s+").map(_.toDouble).toSeq.grouped(192).toSeq
      if (groupedValues.length != 2) return None
      val (pixels, label) = (groupedValues(0), extractLabel(groupedValues(1)))
      if (pixels.length != 192) return None

      Option(new Vector(pixels), label)
    } catch {
      case _: Exception => None
    }
  }

  def parseInput(path: String): Seq[(Vector, Int)] = {
    Source.fromFile(path).getLines.mkString("\n").split("\\n{2}").flatMap(parseLabeledEntry)
  }
}
