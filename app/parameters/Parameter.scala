package parameters

import parameters.ParameterType._

class Parameter(fromI: String, toI: String, parameterTypeI: ParameterType, minCharsI: Int, maxCharsI: Int, minValueI: Int, maxValueI: Int) {
  var from :String = fromI
  var to: String = toI
  var parameterType = parameterTypeI
  var minChars = minCharsI
  var maxChars = maxCharsI
  var minValue = minValueI
  var maxValue = maxValueI
  def validate() {//this must be a trait and validate should take either text, number
	  if  ( parameterType == text) {
	    //TODO
	  }
	}
   override def toString(): String = "(" + from + ", " + to + ")";
}