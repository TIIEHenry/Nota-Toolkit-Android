package tiiehenry.ktx

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/*fun Boolean.judge(f: (String) -> Unit) {

}

public inline fun <T, R> T.judge(block: (T) -> R): R {
    return block(this)
}*/

fun <Boolean, R> Boolean.ifFalse(block: (Boolean) -> R) {
    if (this == false)
        block.invoke(this)
}

fun <Boolean, R> Boolean.ifTrue(block: (Boolean) -> R) {
    if (this==true)
        block.invoke(this)
}

inline fun Any?.ifNull(block: () -> Unit) {
    if (this == null)
        block.invoke()
}
