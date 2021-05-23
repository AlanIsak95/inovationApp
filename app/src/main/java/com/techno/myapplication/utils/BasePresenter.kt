package com.techno.myapplication.utils

/**
 * El tipo de  Presenter.
 * @param <T> el tipo de parametro
</T> */
open class BasePresenter<T : BasePresenter.View?> {

    /**
     * Obtener vista.
     * @return la vista
     */
    private var view: T? = null

    /**
     * Obtener vista.
     *
     * @return la vista
     */
    open fun getView(): T? {
        return view
    }

    /**
     * Sets view.
     * @param view the view
     */
    fun setView(view: T?) {
        this.view = view
    }


    /**
     * La interface de la vista.
     */
    interface View {}

}