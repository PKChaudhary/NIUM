package com.abstractlearning.tutorials.nium.data.model
data class CountryDetails (

	val name : String,
	val topLevelDomain : List<String>,
	val alpha2Code : String,
	val alpha3Code : String,
	val capital : String,
	val altSpellings : List<String>,
	val region : String,
	val subregion : String,
	val demonym : String,
	val timezones : List<String>,
	val borders : List<String>,
	val nativeName : String,
	val currencies : List<Currencies>,
	val languages : List<Languages>,
	val translations : Translations,
	val flag : String,
	val regionalBlocs : List<RegionalBlocs>,
	val cioc : String)