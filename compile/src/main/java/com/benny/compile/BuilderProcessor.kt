package com.benny.compile

import com.benny.annotation.Builder
import com.benny.annotation.Optional
import com.benny.annotation.Required
import com.benny.compile.activity.ActivityClass
import com.benny.compile.activity.entity.Field
import com.bennyhuo.aptutils.AptContext
import com.bennyhuo.aptutils.logger.Logger
import com.bennyhuo.aptutils.types.isSubTypeOf
import com.sun.tools.javac.code.Symbol
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

class BuilderProcessor :AbstractProcessor(){

    //定义可以处理哪些类型的注解，目前就是在annotation中定义的
    private val supportAnnotations = setOf(Builder::class.java,Required::class.java,Optional::class.java)

    override fun getSupportedAnnotationTypes() = supportAnnotations.mapTo(HashSet<String>(),Class<*>::getCanonicalName)
    //支持的java版本
    override fun getSupportedSourceVersion() = SourceVersion.RELEASE_7

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        AptContext.init(processingEnv)
    }

    override fun process(annotations: MutableSet<out TypeElement>, env: RoundEnvironment): Boolean {
        val activityClasses =HashMap<Element,ActivityClass>()
        env.getElementsAnnotatedWith(Builder::class.java)
            .filter { it.kind.isClass }
            .forEach {element: Element ->
                try {
                    if (element.asType().isSubTypeOf("android.app.Activity")){
                    activityClasses[element] = ActivityClass(element as TypeElement)
                    }
                }catch (e:Exception){
                    Logger.logParsingError(element,Builder::class.java,e)
                }

        }
        env.getElementsAnnotatedWith(Required::class.java)
            .filter { it.kind ==ElementKind.FIELD }
            .forEach {element ->
            activityClasses[element.enclosingElement]?.fields?.add(Field(element as Symbol.VarSymbol))?:
            Logger.error(element,"Field $element annotated as Required while ${element.enclosingElement} not annotated")

            }

        env.getElementsAnnotatedWith(Optional::class.java)
            .filter { it.kind ==ElementKind.FIELD }
            .forEach {element ->
                activityClasses[element.enclosingElement]?.fields?.add(Field(element as Symbol.VarSymbol))?:
                Logger.error(element,"Field $element annotated as Optional while ${element.enclosingElement} not annotated")

            }

        activityClasses.values.forEach{
            it.builder.build(AptContext.filer)
        }

        return true;
    }

}