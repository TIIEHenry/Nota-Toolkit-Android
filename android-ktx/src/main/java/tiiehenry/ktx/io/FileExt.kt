package tiiehenry.ktx.io

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

fun File.md5(): String {
    val digest = MessageDigest.getInstance("MD5")
    val bigInt = BigInteger(1, digest.digest(readBytes()))
    return bigInt.toString(16)
}

fun File.child(name:String): File {
    return File(this,name)
}