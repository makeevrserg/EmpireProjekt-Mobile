package com.makeevrserg.empireprojekt.mobile

import java.io.File
import org.jetbrains.kotlin.konan.file.File as KFile
import java.util.Base64

object GradleUtils {
    fun toBase64(file: KFile): String {
        val bytes = file.readBytes()
        return Base64.getEncoder().encodeToString(bytes)
    }

    fun fromBase64(base64: String, output: KFile): KFile {
        val bytes = Base64.getDecoder().decode(base64)
        if (!output.exists) {
            output.parentFile.mkdirs()
            output.createNew()
        }
        output.writeBytes(bytes)
        return output
    }
    fun KFile.child(child: String): KFile {
        return KFile(this, child)
    }
    fun java.io.File.toKFile() = KFile(this.path)
}