
class Vector(val components: Seq[Double]) {
  private def sqr(x: Double) = x * x

  def +(other: Vector): Vector = new Vector(components.zip(other.components).map { case (c1, c2) => c1 + c2 })

  def -(other: Vector): Vector = new Vector(components.zip(other.components).map { case (c1, c2) => c1 - c2 })

  def *(scalar: Double): Vector = new Vector(components.map(c => c * scalar))

  def dotProduct(other: Vector): Double = components.zip(other.components).map {case (c1, c2) => c1 * c2 }.sum

  def normalized: Vector = if (norm() == 0) this else this * (1/norm)

  def norm(): Double = Math.sqrt(components.map(sqr).sum)

  override def equals(that: Any): Boolean =
    that match {
      case that: Vector => that.components.canEqual(components) && that.components.equals(components)
      case _ => false
    }
}
