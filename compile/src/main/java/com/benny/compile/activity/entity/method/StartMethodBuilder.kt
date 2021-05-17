package com.benny.compile.activity.entity.method

import com.benny.compile.activity.ActivityClass
import com.benny.compile.activity.ActivityClassBuilder
import com.benny.compile.activity.entity.OptionalField
import com.squareup.javapoet.TypeSpec

class StartMethodBuilder(private val activityClass: ActivityClass) {

    fun build(typeBuilder:TypeSpec.Builder){
        val startMethod = StartMethod(activityClass,ActivityClassBuilder.METHOD_NAME)

        val groupFields =activityClass.fields.groupBy { it is OptionalField  }
        val requiredFields = groupFields[false]?: emptyList()
        val optionalFields = groupFields[true]?: emptyList()

        startMethod.addAllFields(requiredFields)

        val startMethodNoOptional =startMethod.copy(ActivityClassBuilder.METHOD_NAME_NO_OPTIONAL)

        startMethod.addAllFields(optionalFields)

        startMethod.build(typeBuilder)

        if (optionalFields.isNotEmpty()){
            startMethodNoOptional.build(typeBuilder)
        }
    }
}