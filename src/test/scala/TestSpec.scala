import org.scalatest.freespec.AnyFreeSpec

class TestSpec extends AnyFreeSpec {

  "A test example" - {
    "when try to test something" - {
      "should have the same message 0" in {
        assert("I was compiled by Scala 3. :)" === msg)
      }
    }
  }
}
