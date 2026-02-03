package nota.android.view.spinner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

abstract class SimpleSpinnerAdapter<DATATYPE : Any, VB : ViewBinding>(val vbFactory: (LayoutInflater) -> VB) :
    AbstractSpinnerAdapter<DATATYPE, VB, VB>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        position: Int
    ): SpinnerViewHolder<VB> {
        return SpinnerViewHolder(vbFactory(LayoutInflater.from(parent.context)))
    }

    override fun onCreateDropDownViewHolder(
        parent: ViewGroup,
        position: Int
    ): SpinnerViewHolder<VB> {
        return SpinnerViewHolder(vbFactory(LayoutInflater.from(parent.context)))
    }

    override fun bindData(
        holder: SpinnerViewHolder<VB>,
        item: DATATYPE,
        position: Int
    ) {
        onBindViewHolder(holder, item, position, false)
    }

    override fun bindDropDownData(
        holder: SpinnerViewHolder<VB>,
        item: DATATYPE,
        position: Int
    ) {
        onBindViewHolder(holder, item, position, false)
    }


    abstract fun onBindViewHolder(
        holder: SpinnerViewHolder<VB>,
        item: DATATYPE,
        position: Int,
        dropDown: Boolean
    )


}
