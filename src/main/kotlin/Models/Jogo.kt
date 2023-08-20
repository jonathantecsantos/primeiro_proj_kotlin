package Models

data class Jogo (val titulo: String,
            val capa: String ){

    var descricao:String? = null
    override fun toString(): String {
        return "Titulo= $titulo \n" +
                "Capa= $capa \n" +
                "Descricao= $descricao \n"
    }


}