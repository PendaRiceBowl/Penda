package com.benny.compile.activity.entity.method

import com.benny.compile.activity.ActivityClass
import com.benny.compile.activity.entity.Field
import com.benny.compile.activity.prebuilt.ACTIVITY_BUILDER
import com.benny.compile.activity.prebuilt.CONTEXT
import com.benny.compile.activity.prebuilt.INTENT
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import com.squareup.javapoet.TypeSpec
import com.sun.tools.javadoc.Start
import javax.lang.model.element.Modifier

class StartMethod(private val activityClass: ActivityClass,private val name:String) {

    private val fields = ArrayList<Field>()

    private var isStatic = false

    fun staticMethod(startMethod: Boolean):StartMethod{
        this.isStatic = startMethod
        return this
    }

    fun addAllFields(list : List<Field>){
        fields+=list
    }
    fun addField(field: Field){
        fields+=field
    }
    fun copy(name:String) =StartMethod(activityClass,name).also {
        it.fields.addAll(fields)
    }

    fun build(typeBuilder:TypeSpec.Builder){
        val methodBuilder =MethodSpec.methodBuilder(name)
            .addModifiers(Modifier.PUBLIC)
            .returns(TypeName.VOID)
            .addParameter(CONTEXT.java,"context")

        methodBuilder.addStatement("\$T intent = new \$T(context,\$T.class)", INTENT.java, INTENT.java,activityClass.typeElement)

        fields.forEach {field ->
            val name = field.name
            methodBuilder.addParameter(field.asJavaTypeName(),name)
                .addStatement("intent.putExtra(\$S,\$L)",name,name)
        }
        if (isStatic){
            methodBuilder.addModifiers(Modifier.STATIC)
        }else{
//            methodBuilder.addStatement("fillIntent(intent)")
        }

        methodBuilder.addStatement("\$T.INSTANCE.startActivity(context,intent)", ACTIVITY_BUILDER.java)
        typeBuilder.addMethod(methodBuilder.build())
    }
 }