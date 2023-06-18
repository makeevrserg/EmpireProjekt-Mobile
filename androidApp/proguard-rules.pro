# Optimize
-optimizations !field/*,!class/merging/*,*
-mergeinterfacesaggressively

# will keep line numbers and file name obfuscation
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable

# Apache POI
-dontwarn org.apache.**
-dontwarn org.openxmlformats.schemas.**
-dontwarn org.etsi.**
-dontwarn org.w3.**
-dontwarn com.microsoft.schemas.**
-dontwarn com.graphbuilder.**
-dontwarn javax.naming.**
-dontwarn java.lang.management.**
-dontwarn org.slf4j.impl.**
-dontnote org.apache.**
-dontnote org.openxmlformats.schemas.**
-dontnote org.etsi.**
-dontnote org.w3.**
-dontnote com.microsoft.schemas.**
-dontnote com.graphbuilder.**
-dontnote javax.naming.**
-dontnote java.lang.management.**
-dontnote org.slf4j.impl.**

-keeppackagenames org.apache.poi.ss.formula.function

-keep class aavax.xml.stream.FactoryFinder{*;}
-keep class com.bea.xml.stream.EventFactory{*;}
-keep class org.apache.xmlbeans.XmlBeans{*;}
-keep class org.apache.xmlbeans.SchemaTypeLoader{*;}
# poi-3.12-android-a
-keep class aavax.**{*;}
-keep class com.wutka.**{*;}
-keep class com.bea.**{*;}
-keep class org.apache.**{*;}
-keep class schemaorg_apache_xmlbeans.**{*;}
-keep class org.openxmlformats.**{*;}
-keep class com.makeevrserg.astralearner.utils.xlsx.**{*;}
#-keep class schemaorg_apache_xmlbeans.system.sF1327CCA741569E70F9CA8C9AF9B44B2.TypeSystemHolder { public final static *** typeSystem; }

-keep class org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem { public static *** get(...); public static *** getNoType(...); }
-keep class org.apache.xmlbeans.impl.schema.PathResourceLoader { public <init>(...); }
-keep class org.apache.xmlbeans.impl.schema.SchemaTypeSystemCompiler { public static *** compile(...); }
#-keep class org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl { public <init>(...); public static *** get(...); public static *** getNoType(...); }
-keep class org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl { public static *** getContextTypeLoader(...); public static *** build(...); }
-keep class org.apache.xmlbeans.impl.store.Locale { public static *** streamToNode(...); public static *** nodeTo*(...); }
-keep class org.apache.xmlbeans.impl.store.Path { public static *** compilePath(...); }
-keep class org.apache.xmlbeans.impl.store.Query { public static *** compileQuery(...); }

-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CommentsDocument { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAuthors { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBooleanProperty { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookView { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBookViews { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorders { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorderPr { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCell { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellFormula { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellXfs { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComment { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTComments { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCommentList { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDrawing { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFills { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFonts { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontName { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontScheme { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontSize { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTLegacyDrawing { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmts { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPageMargins { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPane { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSelection { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetData { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetDimension { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetFormatPr { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetView { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetViews { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSst { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbookPr { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellType$Enum { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.STCellFormulaType$Enum { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring { *; }

-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CommentsDocumentImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTAuthorsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBooleanPropertyImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBookViewImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBookViewsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBorderImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBordersImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTBorderPrImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCellImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCellAlignmentImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCellFormulaImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCellStyleXfsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCellXfsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTColorImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTColImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTColsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCommentImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCommentsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTCommentListImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTDrawingImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFillImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFillsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontNameImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontSchemeImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTFontSizeImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTIntPropertyImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTLegacyDrawingImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTNumFmtsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPatternFillImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPageMarginsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTPaneImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRowImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSelectionImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetDataImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetDimensionImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetFormatPrImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetViewImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetViewsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSheetsImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTSstImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTStylesheetImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorkbookImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorkbookPrImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTWorksheetImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTXfImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.SstDocumentImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.StyleSheetDocumentImpl { *; }
-keep class org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.STXstringImpl { *; }

-keep class org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.CTPropertiesImpl { *; }
-keep class org.openxmlformats.schemas.officeDocument.x2006.customProperties.impl.PropertiesDocumentImpl { *; }
-keep class org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl.CTPropertiesImpl { *; }
-keep class org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.impl.PropertiesDocumentImpl { *; }
-keep class org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTDrawingImpl { *; }
-keep class org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTMarkerImpl { *; }
#-keep class com.microsoft.schemas.office.office.impl.CTIdMapImpl { *; }
#-keep class com.microsoft.schemas.office.office.impl.CTShapeLayoutImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTShadowImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTFillImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTPathImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTShapeImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTShapetypeImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTStrokeImpl { *; }
#-keep class com.microsoft.schemas.vml.impl.CTTextboxImpl { *; }
#-keep class com.microsoft.schemas.office.excel.impl.CTClientDataImpl { *; }
#-keep class com.microsoft.schemas.office.excel.impl.STTrueFalseBlankImpl { *; }

# KotlinX Serialization
# Keep `Companion` object fields of serializable classes.
# This avoids serializer lookup through `getDeclaredClasses` as done for named companion objects.
-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
    static <1>$Companion Companion;
}

# Keep `serializer()` on companion objects (both default and named) of serializable classes.
-if @kotlinx.serialization.Serializable class ** {
    static **$* *;
}
-keepclassmembers class <2>$<3> {
    kotlinx.serialization.KSerializer serializer(...);
}

# Keep `INSTANCE.serializer()` of serializable objects.
-if @kotlinx.serialization.Serializable class ** {
    public static ** INSTANCE;
}
-keepclassmembers class <1> {
    public static <1> INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}

# @Serializable and @Polymorphic are used at runtime for polymorphic serialization.
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault