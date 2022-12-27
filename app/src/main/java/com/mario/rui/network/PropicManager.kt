package com.mario.rui.network

class PropicManager {
    companion object {
        fun getPropicUrl(owner: String): String {
            if (owner.lowercase() == "mario rui") {
                return "https://www.ilnapolista.it/wp-content/uploads/2022/07/Mario_Rui_MG0_3984-e1658081286947.jpg"
            }

            return "https://www.ilnapolista.it/wp-content/uploads/2022/07/Mario_Rui_MG0_3984-e1658081286947.jpg"
        }
    }
}