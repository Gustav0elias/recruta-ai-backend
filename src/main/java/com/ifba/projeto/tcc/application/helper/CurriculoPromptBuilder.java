package com.ifba.projeto.tcc.application.helper;

public class CurriculoPromptBuilder {

    public static String ConstruirPromptCandidato(String conteudo){
        return """
    Extraia do texto do currículo abaixo e devolva **somente** um JSON válido. 
    **Não inclua explicações, markdown ou outro texto.**

    **Atenção:** todos os campos string devem ter **aspas duplas escapadas** e **quebras de linha removidas**.  
    Respeite os tamanhos máximos de cada campo:
    - nome: até 50 caracteres
    - email: até 50 caracteres
    - telefone: até 12 caracteres
    - habilidades.nome: até 50 caracteres
    - habilidades.tipo: apenas valores válidos (ex: HABILIDADE_TECNICA, SOFT_SKILL)
    - experiencias.empresa: até 50 caracteres
    - experiencias.cargo: até 50 caracteres
    - experiencias.descricao: até 100 caracteres
    - datas: formato AAAA-MM-DD (caso o profissinal esteja atualmente em algum emprego o "dataFim" fica como null

    JSON esperado:
    {
      "nome": "",
      "email": "",
      "telefone": "",
      "habilidades": [
        {
          "nome": "",
          "tipo": ""
        }
      ],
      "experiencias": [
        {
          "empresa": "",
          "cargo": "",
          "descricao": "",
          "dataInicio": "",
          "dataFim": ""
        }
      ]
    }

    Texto do currículo:
    """ + conteudo;

    }
}