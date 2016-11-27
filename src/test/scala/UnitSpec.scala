import org.scalatest._
import org.scalatest.prop.GeneratorDrivenPropertyChecks

abstract class UnitSpec extends FunSpec
  with Matchers
  with OptionValues
  with Inside
  with GeneratorDrivenPropertyChecks