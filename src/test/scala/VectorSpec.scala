
import org.scalacheck.{Arbitrary, Gen}

import scala.util.Random

class VectorSpec extends UnitSpec {

  val random = new Random

  lazy val vectorGen: Gen[Vector] = {
    for {
      dimensions <- Gen.choose(2, 100)
      seq <- Gen.listOfN(dimensions, Gen.choose(-1.0, 1.0))
    } yield new Vector(seq)
  }

  implicit lazy val arbVector:Arbitrary[Vector] = Arbitrary(vectorGen)

  val vectorPairGen: Gen[(Vector,Vector)] = {
      for {
        v1 <- Arbitrary.arbitrary[Vector]
        v2 <- Arbitrary.arbitrary[Vector]
      } yield (v1, v2)
    }

  def createVector(seq: Seq[Double]) = new Vector(seq)

  describe("A vector") {
    it("when added to another vector should have components that correspond to the sum of the individual components") {
      val v1 = createVector(Seq(2, 42, 95))
      val v2 = createVector(Seq(40, 0, -53))
      val expected = createVector(Seq(42, 42, 42))

      assertResult(expected) {
        v1 + v2
      }
    }

    it("when subtracted from another vector should leave the latter with components that have the previous value minus the other vectors component value") {
      val v1 = createVector(Seq(82, 42, 95))
      val v2 = createVector(Seq(40, 0, 53))
      val expected = createVector(Seq(42,42,42))

      assertResult(expected) {
        v1 - v2
      }
    }

    it("when multiplied by scalar have each component multiplied by it") {
      val v1 = createVector(Seq(3, 7, 21))
      val scalar = 2
      val expected = createVector(Seq(6,14,42))

      assertResult(expected) {
        v1 * scalar
      }
    }

    it("when combined with another vector to calculate the dot product should be such that the result is the sum of the components multiplied by each other") {
      val v1 = createVector(Seq(3, 7, 21))
      val v2 = createVector(Seq(4, 9, 5))
      val expected = 180

      assertResult(expected) {
        v1.dotProduct(v2)
      }
    }

    it("should be commutative regarding addition") {
      forAll(vectorPairGen) {
        case (v1:Vector, v2:Vector) => v1 + v2 shouldEqual v2 + v1
      }
    }

    it("should return a norm that corresponds to the sqrt of the sum of all squared components") {
      val v = createVector(Seq(3, 5, 4, 2, 9))
      val expected: Double = Math.sqrt(3*3 + 5*5 + 4*4 + 2*2 + 9*9)

      expected shouldEqual v.norm
    }

    it("when normalized should have a value of 1 if it is a non-zero vector") {
      forAll(vectorGen) { v:Vector =>
        if (v.norm == 0.0)
          v.normalized.norm shouldEqual 0
        else
          Math.abs(v.normalized.norm - 1.0) should be <= 0.0000001
      }
    }
  }
}



