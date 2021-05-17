package com.benny.compile.activity

import com.benny.compile.activity.entity.Field
import com.bennyhuo.aptutils.types.packageName
import com.bennyhuo.aptutils.types.simpleName
import java.util.*
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

class ActivityClass(val typeElement: TypeElement) {
    val simpleName :String = typeElement.simpleName()
    val packageName:String = typeElement.packageName()

    val fields = TreeSet<Field>()

    val builder=ActivityClassBuilder(this)

    //是否是抽象类，如果是抽象类则不会生成相应的新类
    val isAbstract = typeElement.modifiers.contains(Modifier.ABSTRACT)

    val isKotlin =typeElement.getAnnotation(Metadata::class.java)!=null

    override fun toString(): String {
        return "$packageName $simpleName ${fields.joinToString()}"
    }
}