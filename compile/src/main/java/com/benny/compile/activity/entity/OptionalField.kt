package com.benny.compile.activity.entity

import com.benny.annotation.Optional
import com.sun.tools.javac.code.Symbol
import javax.lang.model.type.TypeKind
import javax.lang.model.type.TypeMirror


class OptionalField(symbol: Symbol.VarSymbol):Field(symbol) {

    var defaultValue:Any? = null
    private set

    override val prefix: String ="OPTIONAL_"

    init {
        val optional =symbol.getAnnotation(Optional::class.java)
        when(symbol.type.kind){
            TypeKind.BOOLEAN ->defaultValue =optional.booleanValue
            TypeKind.BYTE,TypeKind.SHORT,TypeKind.INT,TypeKind.LONG,TypeKind.CHAR ->
                defaultValue = optional.intValue
            TypeKind.FLOAT,TypeKind.DOUBLE ->defaultValue = optional.floatValue
            else ->if (symbol.type== String::class.java as TypeMirror){
                defaultValue=""""${optional.stringValue}""""
            }
        }
    }

    override fun compareTo(other: Field): Int {
       return if (other is OptionalField){
          super.compareTo(other)
       }else{
           1
       }
    }
}