package com.example.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel : ViewModel() {

    private val _shoes: MutableLiveData<Set<ShoeModel>> = MutableLiveData(emptySet())


    val shoes: LiveData<Set<ShoeModel>>
        get() = _shoes


    private val _doneAdding: MutableLiveData<Boolean> = MutableLiveData(false)
    val doneAdding: LiveData<Boolean>
        get() = _doneAdding


    private val _goToDetail: MutableLiveData<Boolean> = MutableLiveData(false)
    val goToDetail: LiveData<Boolean>
        get() = _goToDetail

    private val _selectedShoe: MutableLiveData<ShoeModel> = MutableLiveData(ShoeModel.empty)


    val selectedShoe: LiveData<ShoeModel>
        get() = _selectedShoe


    fun onShoePressed(shoe: ShoeModel) {

        _selectedShoe.value = shoe
        _goToDetail.value = true
    }

    fun resetDoneAdding() {
        _doneAdding.value = false
    }


    fun shoeNameChanged(v: String) {
        _selectedShoe.value = _selectedShoe.value!!.copy(name = v)
    }

    fun shoeDescribtionChanged(v: String) {
        _selectedShoe.value = _selectedShoe.value!!.copy(describtion = v)
    }

    fun shoeSizeChanged(v: String) {
        _selectedShoe.value = _selectedShoe.value!!.copy(size = v)
    }

    fun shoeCompanyChanged(v: String) {
        _selectedShoe.value = _selectedShoe.value!!.copy(company = v)
    }


    fun shoePriceChanged(v: String) {
        val parsed = v.toDoubleOrNull()
        val newPrice = parsed ?: _selectedShoe.value!!.price
        _selectedShoe.value =
            _selectedShoe.value!!.copy(price = newPrice)

    }


    fun shoeQtyChanged(v: String) {
        val parsed = v.toIntOrNull()
        val newQty = parsed ?: _selectedShoe.value!!.qty
        _selectedShoe.value = _selectedShoe.value!!.copy(qty = newQty)

    }

    fun resetGoToDetail() {
        _goToDetail.value = false

    }


    fun closeDetailsFrag() {
        _doneAdding.value = true
    }

    fun addShoe(

    ) {

        val list = _shoes.value ?: setOf()
        val newList = list.toMutableSet()

        newList.add(
            _selectedShoe.value!!.copy(
                id = _selectedShoe.value!!.id.ifEmpty { (_shoes.value!!.size + 1).toString() },
            )
        )
        _shoes.value = newList
        clearShoe()
        closeDetailsFrag()
    }


    fun clearShoe() {
        _selectedShoe.value = ShoeModel.empty

    }
}