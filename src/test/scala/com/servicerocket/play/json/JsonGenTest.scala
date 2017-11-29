package com.servicerocket.play.json

import play.api.libs.json._
import org.scalatest.{Matchers, WordSpec}
import com.servicerocket.play.json.JsonGen.{jsBooleanGen, _}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

/** Property-driven test for Play Json generators.
  *
  * @author Nader Hadji Ghanbari
  */
class JsonGenTest extends WordSpec with Matchers with GeneratorDrivenPropertyChecks {

  "Json Generators" should {

    "generate JsNull" in forAll(jsNullGen) { json =>
      json shouldBe a[JsNull.type]
    }

    "generate JsString" in forAll(jsStringGen) { json =>
      json shouldBe a[JsString]
    }

    "generate non-empty JsString" in forAll(nonEmptyJsStringGen) { json =>
      json shouldBe a[JsString]
      json.value should not be empty
    }

    "generate JsBoolean" in forAll(jsBooleanGen) { json =>
      json shouldBe a[JsBoolean]
    }

    "generate JsNumber" in forAll(jsNumberGen) { json =>
      json shouldBe a[JsNumber]
      json.value.toInt should be(0 +- 1000000)
    }

    "generate JsObject" in forAll(jsObjectGen) { json =>
      json shouldBe a[JsObject]
      json.keys.size should be >= 0
      json.keys.size should be <= 3
    }

    "generate non-empty JsObject" in forAll(nonEmptyJsObjectGen) { json =>
      json shouldBe a[JsObject]
      json.keys should not be empty
      json.keys.size should be <= 3
    }

    "generate jsArray" in forAll(jsArrayGen) { json =>
      json shouldBe a[JsArray]
      json.value.size should be >= 0
      json.value.size should be <= 3
    }

    "generate non-empty JsArray" in forAll(nonEmptyJsArrayGen) { json =>
      json shouldBe a[JsArray]
      json.value should not be empty
      json.value.size should be <= 3
    }

    "generate JsValue" in forAll(jsValueGen) { json =>
      json shouldBe a[JsValue]
    }

    "generate non-empty JsValue" in forAll(nonEmptyJsValueGen) { json =>
      json shouldBe a[JsValue]
      json should not be a[JsNull.type]
    }

  }

}
