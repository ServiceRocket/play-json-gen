package com.servicerocket.play.json

import org.scalacheck.Gen
import org.scalacheck.Gen._
import play.api.libs.json._

/** Play Json ScalaCheck generators.
  *
  * @author Nader Hadji Ghanbari
  */
object JsonGen {

  /** Constant generator for JsNull.
    *
    * @return JsNull generator.
    */
  def jsNullGen: Gen[JsNull.type] = const(JsNull)

  /** JsString generator. The string value is generated using ScalaCheck's alphaStr generator.
    *
    * @return JsString generator.
    */
  def jsStringGen: Gen[JsString] = alphaStr map JsString

  /** Non-empty JsString generator.
    *
    * @return JsString generator.
    */
  def nonEmptyJsStringGen: Gen[JsString] = jsStringGen.filter(_.value.nonEmpty)

  /** JsBoolean generator.
    *
    * @return JsBoolean generator.
    */
  def jsBooleanGen: Gen[JsBoolean] = oneOf(true, false) map JsBoolean

  /** JsNumber generator. Generated number will be in the range [-1000,000 1000,000].
    *
    * @return JsNumber generator.
    */
  def jsNumberGen: Gen[JsNumber] = jsNumberGen()

  /** JsNumber generator. Generated number will be in the provided range.
    *
    * @param min Range min.
    * @param max Range max.
    * @return JsNumber generator.
    */
  def jsNumberGen(min: Long = -1000000, max: Long = 1000000): Gen[JsNumber] =
    chooseNum[Long](min, max).map(BigDecimal.valueOf).map(JsNumber)

  /** JsObject generator. Fan-out is in [0, 3] range.
    *
    * @return JsObject generator.
    */
  def jsObjectGen: Gen[JsObject] = for {
    fanOut <- choose(0, 3)
    fields <- listOfN(fanOut, zip(identifier, jsValueGen))
  } yield JsObject(fields)

  /** JsObject generator. Fan-out is in [1, 3] range.
    *
    * @return JsObject generator.
    */
  def nonEmptyJsObjectGen: Gen[JsObject] = for {
    fanOut <- choose(1, 3)
    fields <- listOfN(fanOut, zip(identifier, nonEmptyJsValueGen))
  } yield JsObject(fields)

  /** JsArray generator. Length lies between [1, 3].
    *
    * @return JsArray generator.
    */
  def jsArrayGen: Gen[JsArray] = for {
    length <- chooseNum(0, 3)
    fields <- listOfN(length, jsValueGen)
  } yield JsArray(fields)

  /** JsArray generator. Length lies between [0, 3].
    *
    * @return JsArray generator.
    */
  def nonEmptyJsArrayGen: Gen[JsArray] = for {
    length <- chooseNum(1, 3)
    fields <- listOfN(length, jsValueGen)
  } yield JsArray(fields)

  /** JsValue generator.
    *
    * @return JsValue generator.
    */
  def jsValueGen: Gen[JsValue] = oneOf(jsNullGen, jsStringGen, jsBooleanGen, jsNumberGen, jsObjectGen, jsArrayGen)

  /** Non empty JsValue generator.
    *
    * @return JsValue generator.
    */
  def nonEmptyJsValueGen: Gen[JsValue] = oneOf(jsStringGen, jsBooleanGen, jsNumberGen, jsObjectGen, nonEmptyJsArrayGen)

}
