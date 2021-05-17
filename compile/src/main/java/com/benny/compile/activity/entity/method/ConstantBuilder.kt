package com.benny.compile.activity.entity.method

import com.benny.compile.activity.ActivityClass
import com.squareup.javapoet.FieldSpec
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Modifier

class ConstantBuilder(private val activityClass: ActivityClass) {

    fun build(typeBuilder: TypeSpec.Builder) {
        activityClass.fields.forEach { field ->
            typeBuilder.addField(
                FieldSpec.builder(
                    String::class.java,
                    field.prefix + field.name.toUpperCase(),
                    Modifier.PUBLIC,
                    Modifier.STATIC,
                    Modifier.FINAL
                ).initializer("\$S", field.name)
                    .build()
            )

        }
    }
}