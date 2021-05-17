package com.benny.compile.activity

import com.benny.compile.activity.entity.method.ConstantBuilder
import com.benny.compile.activity.entity.method.StartMethodBuilder
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.Filer
import javax.lang.model.element.Modifier

class ActivityClassBuilder(private val activityClass: ActivityClass) {

    companion object{
        const val POSIX = "Builder"
        const val METHOD_NAME="start"
        const val METHOD_NAME_NO_OPTIONAL= METHOD_NAME+"WithoutOptional"
        const val METHOD_NAME_FOR_OPTIONAL= METHOD_NAME+"WithOptional"
        const val METHOD_NAME_FOR_OPTIONALs= METHOD_NAME+"WithOptionals"
    }

    fun build(filer: Filer){
        if (activityClass.isAbstract) return
        val typeBuilder =TypeSpec.classBuilder(activityClass.simpleName+ POSIX)
            .addModifiers(Modifier.PUBLIC,Modifier.FINAL)


        ConstantBuilder(activityClass).build(typeBuilder)

        StartMethodBuilder(activityClass).build(typeBuilder)

        writeJavaToFile(filer,typeBuilder.build())
    }

    private fun writeJavaToFile(filer: Filer,typeSpec: TypeSpec){
        val file = JavaFile.builder(activityClass.packageName,typeSpec).build()
        file.writeTo(filer)
    }
}