class InputParserSpec extends UnitSpec {

  val inputParser: InputParser = new InputParser

  describe("The inputparser") {
    it("should successfully parse this entry and return a vector and label 3") {

      val rawInput =
        """0.0 0.0 0.0 0.0 0.3 1.0 1.0 0.8 0.4 0.0 0.0 0.0
        0.0 0.0 0.0 0.4 1.0 0.3 0.3 0.4 0.8 0.6 0.0 0.0
        0.0 0.0 0.1 0.9 0.2 0.0 0.0 0.0 0.1 1.0 0.0 0.0
        0.0 0.0 0.8 0.5 0.0 0.0 0.0 0.0 0.0 1.0 0.3 0.0
        0.0 0.6 0.7 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.1 0.0
        0.5 0.8 0.1 0.0 0.0 0.0 0.0 0.0 0.2 0.8 0.0 0.0
        0.8 0.5 0.0 0.0 0.0 0.0 0.0 0.0 0.9 0.3 0.0 0.0
        0.0 0.0 0.0 0.0 0.0 0.0 0.2 0.8 0.7 0.0 0.0 0.0
        0.0 0.0 0.0 0.0 0.0 0.0 1.0 1.0 1.0 0.4 0.0 0.0
        0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.2 0.8 0.8 0.0
        0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.1 0.8 0.4
        0.0 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.6 0.9
        0.0 0.7 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.4 0.8
        0.0 0.5 0.8 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.5 0.8
        0.0 0.0 0.6 1.0 0.8 0.3 0.0 0.0 0.2 0.6 1.0 0.3
        0.0 0.0 0.0 0.2 0.4 0.7 0.8 0.9 0.8 0.5 0.1 0.0
        0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0 0.0"""

      val (expectedVector, expectedLabel) = (new Vector(Seq(0.0, 0.0, 0.0, 0.0, 0.3, 1.0, 1.0, 0.8, 0.4, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.4, 1.0, 0.3, 0.3, 0.4, 0.8, 0.6, 0.0, 0.0,
        0.0, 0.0, 0.1, 0.9, 0.2, 0.0, 0.0, 0.0, 0.1, 1.0, 0.0, 0.0,
        0.0, 0.0, 0.8, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.3, 0.0,
        0.0, 0.6, 0.7, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.1, 0.0,
        0.5, 0.8, 0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.2, 0.8, 0.0, 0.0,
        0.8, 0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.9, 0.3, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.2, 0.8, 0.7, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.4, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.2, 0.8, 0.8, 0.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.8, 0.4,
        0.0, 0.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.6, 0.9,
        0.0, 0.7, 0.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.4, 0.8,
        0.0, 0.5, 0.8, 0.2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.5, 0.8,
        0.0, 0.0, 0.6, 1.0, 0.8, 0.3, 0.0, 0.0, 0.2, 0.6, 1.0, 0.3,
        0.0, 0.0, 0.0, 0.2, 0.4, 0.7, 0.8, 0.9, 0.8, 0.5, 0.1, 0.0)), 3)

      val (actualVector, actualLabel) = inputParser.parseLabeledEntry(rawInput).get
      expectedVector shouldEqual actualVector
      expectedLabel shouldEqual actualLabel
    }

    it("should return None since label is not set") {

      val rawInput =
        """0.0 0.0 0.0 0.0 0.3 1.0 1.0 0.8 0.4 0.0 0.0 0.0
      0.0 0.0 0.0 0.4 1.0 0.3 0.3 0.4 0.8 0.6 0.0 0.0
      0.0 0.0 0.1 0.9 0.2 0.0 0.0 0.0 0.1 1.0 0.0 0.0
      0.0 0.0 0.8 0.5 0.0 0.0 0.0 0.0 0.0 1.0 0.3 0.0
      0.0 0.6 0.7 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.1 0.0
      0.5 0.8 0.1 0.0 0.0 0.0 0.0 0.0 0.2 0.8 0.0 0.0
      0.8 0.5 0.0 0.0 0.0 0.0 0.0 0.0 0.9 0.3 0.0 0.0
      0.0 0.0 0.0 0.0 0.0 0.0 0.2 0.8 0.7 0.0 0.0 0.0
      0.0 0.0 0.0 0.0 0.0 0.0 1.0 1.0 1.0 0.4 0.0 0.0
      0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.2 0.8 0.8 0.0
      0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.1 0.8 0.4
      0.0 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.6 0.9
      0.0 0.7 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.4 0.8
      0.0 0.5 0.8 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.5 0.8
      0.0 0.0 0.6 1.0 0.8 0.3 0.0 0.0 0.2 0.6 1.0 0.3
      0.0 0.0 0.0 0.2 0.4 0.7 0.8 0.9 0.8 0.5 0.1 0.0
      0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0"""

      assertResult(None) {
        inputParser.parseLabeledEntry(rawInput)
      }
    }

    it("should return None since the raw label has wrong size") {

      val rawInput =
        """0.0 0.0 0.0 0.0 0.3 1.0 1.0 0.8 0.4 0.0 0.0 0.0
      0.0 0.0 0.0 0.4 1.0 0.3 0.3 0.4 0.8 0.6 0.0 0.0
      0.0 0.0 0.1 0.9 0.2 0.0 0.0 0.0 0.1 1.0 0.0 0.0
      0.0 0.0 0.8 0.5 0.0 0.0 0.0 0.0 0.0 1.0 0.3 0.0
      0.0 0.6 0.7 0.0 0.0 0.0 0.0 0.0 0.0 1.0 0.1 0.0
      0.5 0.8 0.1 0.0 0.0 0.0 0.0 0.0 0.2 0.8 0.0 0.0
      0.8 0.5 0.0 0.0 0.0 0.0 0.0 0.0 0.9 0.3 0.0 0.0
      0.0 0.0 0.0 0.0 0.0 0.0 0.2 0.8 0.7 0.0 0.0 0.0
      0.0 0.0 0.0 0.0 0.0 0.0 1.0 1.0 1.0 0.4 0.0 0.0
      0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.2 0.8 0.8 0.0
      0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.1 0.8 0.4
      0.0 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.6 0.9
      0.0 0.7 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.4 0.8
      0.0 0.5 0.8 0.2 0.0 0.0 0.0 0.0 0.0 0.0 0.5 0.8
      0.0 0.0 0.6 1.0 0.8 0.3 0.0 0.0 0.2 0.6 1.0 0.3
      0.0 0.0 0.0 0.2 0.4 0.7 0.8 0.9 0.8 0.5 0.1 0.0
      0.0 0.0 0.0 1.0 0.0 0.0 0.0 0.0 0.0"""

      assertResult(None) {
        inputParser.parseLabeledEntry(rawInput)
      }
    }
  }
}



