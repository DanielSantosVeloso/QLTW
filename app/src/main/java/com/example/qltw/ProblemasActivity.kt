package com.example.qltw

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.material.navigation.NavigationView

class ProblemasActivity : AppCompatActivity() {
    private lateinit var saidaV: TextView
    private lateinit var chegadaV: TextView
    private lateinit var valorV: TextView

    private lateinit var problemaB1: Button
    private lateinit var problemaB2: Button
    private lateinit var problemaB3: Button
    private lateinit var problemaB4: Button
    private lateinit var problemaB5: Button

    private lateinit var descricao: TextView
    private lateinit var buttonVoltar: ImageButton

    private var contador = 0
    private var problemaSelecionado = 0

    private val problemas = listOf(
        problemas(
            "Despressurização da cabine",
            "Falha de motor",
            "Turbulência severa",
            "Congelamento de instrumentos",
            "Pane elétrica"
        ),
        problemas(
            "Incêndio a bordo",
            "Falha nos sistemas de controle de voo",
            "Vazamento de combustível",
            "Colisão com pássaros",
            "Problemas de comunicação com a torre de controle"
        ),
        problemas(
            "Desorientação espacial dos pilotos",
            "Problemas de saúde de passageiros",
            "Congelamento das asas",
            "Desgaste do revestimento externo da aeronave",
            "Bloqueio dos sistemas de navegação"
        ),
        problemas(
            "Problemas com os sistemas de pressurização",
            "Fumaça na cabine",
            "Falha no sistema de trem de pouso",
            "Falha nos sistemas de combustível",
            "Desvio de rota devido a restrições de espaço aéreo"
        ),
        problemas(
            "Problemas com os sistemas de oxigênio",
            "Falha nos sistemas de controle do clima",
            "Perda de carga durante o voo",
            "Erro de navegação",
            "Atividade terrorista ou sequestro"
        ),
        problemas(
            "Problemas nos sistemas de entretenimento",
            "Ataque de drones",
            "Mal funcionamento dos sistemas de freios",
            "Falha nos sistemas de iluminação",
            "Falha nos sistemas de comunicação interna"
        ),
        problemas(
            "Ataque de animais a bordo",
            "Problemas com os sistemas de descarga de resíduos",
            "Falha nos sistemas de extinção de incêndio",
            "Ataque cibernético aos sistemas da aeronave",
            "Falha nos sistemas de navegação por GPS"
        ),
        problemas(
            "Danos causados por granizo",
            "Problemas de estabilidade aerodinâmica",
            "Falha nos sistemas de alerta de proximidade do solo",
            "Problemas com os sistemas de comunicação de emergência",
            "Despressurização do compartimento de carga"
        ),
        problemas(
            "Falha nos sistemas de travamento das portas",
            "Problemas de estabilização de temperatura na cabine",
            "Falha nos sistemas de gerenciamento de combustível",
            "Emergência médica na tripulação",
            "Problemas com os sistemas de ventilação da cabine"
        ),
        problemas(
            "Falha nos sistemas de descarga de combustível",
            "Perda de comunicação com aeronaves próximas",
            "Problemas nos sistemas de ativação dos coletes salva-vidas",
            "Falha nos sistemas de iluminação de emergência",
            "Bloqueio dos sistemas de comando de voo por interferência externa"
        ),
        problemas(
            "Despressurização da cabine",
            "Falha de motor",
            "Turbulência severa",
            "Congelamento de instrumentos",
            "Pane elétrica"
        ),
        problemas(
            "Incêndio a bordo",
            "Falha nos sistemas de controle de voo",
            "Vazamento de combustível",
            "Colisão com pássaros",
            "Problemas de comunicação com a torre de controle"
        ),
        problemas(
            "Desorientação espacial dos pilotos",
            "Problemas de saúde de passageiros",
            "Congelamento das asas",
            "Desgaste do revestimento externo da aeronave",
            "Bloqueio dos sistemas de navegação"
        ),
        problemas(
            "Problemas com os sistemas de pressurização",
            "Fumaça na cabine",
            "Falha no sistema de trem de pouso",
            "Falha nos sistemas de combustível",
            "Desvio de rota devido a restrições de espaço aéreo"
        ),
        problemas(
            "Problemas com os sistemas de oxigênio",
            "Falha nos sistemas de controle do clima",
            "Perda de carga durante o voo",
            "Erro de navegação",
            "Atividade terrorista ou sequestro"
        ),
        problemas(
            "Problemas nos sistemas de entretenimento",
            "Ataque de drones",
            "Mal funcionamento dos sistemas de freios",
            "Falha nos sistemas de iluminação",
            "Falha nos sistemas de comunicação interna"
        ),
        problemas(
            "Ataque de animais a bordo",
            "Problemas com os sistemas de descarga de resíduos",
            "Falha nos sistemas de extinção de incêndio",
            "Ataque cibernético aos sistemas da aeronave",
            "Falha nos sistemas de navegação por GPS"
        ),
        problemas(
            "Danos causados por granizo",
            "Problemas de estabilidade aerodinâmica",
            "Falha nos sistemas de alerta de proximidade do solo",
            "Problemas com os sistemas de comunicação de emergência",
            "Despressurização do compartimento de carga"
        ),
        problemas(
            "Falha nos sistemas de travamento das portas",
            "Problemas de estabilização de temperatura na cabine",
            "Falha nos sistemas de gerenciamento de combustível",
            "Emergência médica na tripulação",
            "Problemas com os sistemas de ventilação da cabine"
        ),
        problemas(
            "Falha nos sistemas de descarga de combustível",
            "Perda de comunicação com aeronaves próximas",
            "Problemas nos sistemas de ativação dos coletes salva-vidas",
            "Falha nos sistemas de iluminação de emergência",
            "Bloqueio dos sistemas de comando de voo por interferência externa"
        ),
    )

    private val descricoes = listOf(
        descricaoProblemas(
            "Perda da pressão interna da aeronave, o que pode levar à falta de oxigênio e problemas respiratórios para os passageiros e tripulação.",
            "Um dos motores da aeronave para de funcionar, podendo exigir procedimentos de emergência, como um pouso de emergência.",
            "Movimentos bruscos e imprevisíveis da aeronave devido a correntes de ar instáveis, causando desconforto e potencialmente ferimentos aos passageiros.",
            "Formação de gelo nos instrumentos de voo, dificultando a leitura e interferindo na capacidade dos pilotos de monitorar e controlar a aeronave.",
            "Falha nos sistemas elétricos da aeronave, afetando sistemas essenciais como comunicações, navegação e iluminação."
        ),
        descricaoProblemas(
            "Ocorrência de fogo dentro da aeronave, exigindo ação rápida da tripulação para extinguir o fogo e garantir a segurança dos passageiros.",
            "Problemas nos sistemas que controlam a altitude, direção e estabilidade da aeronave, podendo levar a uma perda de controle.",
            "Escape de combustível da aeronave, representando um risco de incêndio ou perda de potência dos motores.",
            "Impacto da aeronave com aves durante o voo, podendo danificar os motores ou outras partes da aeronave.",
            "Dificuldades na transmissão ou recepção de mensagens entre a aeronave e os controladores de tráfego aéreo, afetando a segurança e eficiência do voo."
        ),
        descricaoProblemas(
            "Situação em que os pilotos perdem a noção correta da posição, atitude e movimento da aeronave, geralmente devido a condições meteorológicas adversas.",
            "Emergências médicas a bordo, como ataques cardíacos, reações alérgicas ou desmaios, exigindo atenção imediata da tripulação.",
            "Deterioração da estrutura externa da aeronave devido a fatores como corrosão, fadiga ou danos mecânicos.",
            " Acúmulo de gelo nas asas da aeronave, prejudicando sua capacidade de sustentação e aerodinâmica.",
            "Interrupção ou falha nos sistemas de navegação da aeronave, dificultando a determinação da posição e rota correta."
        ),
        descricaoProblemas(
            "Falhas nos sistemas que mantêm a pressão da cabine adequada, podendo causar desconforto ou problemas de saúde nos passageiros.",
            " Presença de fumaça no interior da aeronave, indicando a possibilidade de incêndio ou mau funcionamento dos sistemas de ventilação.",
            "Problemas com os mecanismos que baixam ou travam o trem de pouso da aeronave, exigindo procedimentos especiais durante o pouso.",
            " Problemas nos sistemas de armazenamento e distribuição de combustível da aeronave, podendo levar a perda de potência dos motores. ",
            " Alteração na rota planejada devido a restrições ou fechamento de áreas do espaço aéreo, causando atrasos ou aumento do consumo de combustível."
        ),
        descricaoProblemas(
            "Falhas nos sistemas de fornecimento de oxigênio de emergência, comprometendo a segurança dos passageiros em caso de despressurização.",
            "Problemas nos sistemas de controle de temperatura e umidade da cabine, afetando o conforto dos passageiros.",
            "Solução ou queda de carga da aeronave durante o voo, representando perigo para a segurança da aeronave e pessoas em terra.",
            "Desvio da rota planejada devido a erros de navegação ou cálculo incorreto das condições meteorológicas, podendo aumentar o tempo de voo e o consumo de combustível.",
            "Tentativa de sabotagem, ataque terrorista ou sequestro da aeronave, representando grave ameaça à segurança dos passageiros e tripulação."
        ),
        descricaoProblemas(
            "Falhas nos sistemas de entretenimento a bordo, causando desconforto e frustração entre os passageiros.",
            " Colisão da aeronave com drones não autorizados, podendo danificar os motores ou outras partes da aeronave.",
            "Problemas nos sistemas de freios da aeronave, tornando o pouso e a desaceleração mais desafiadores e potencialmente perigosos.",
            "Problemas nos sistemas de iluminação da cabine ou pista de pouso, afetando a visibilidade e a segurança durante o voo e pouso.",
            "Interrupção ou falha nos sistemas de comunicação interna da aeronave, dificultando a coordenação entre a tripulação."
        ),
        descricaoProblemas(
            "Presença de animais a bordo da aeronave, como roedores, representando riscos à saúde e segurança dos passageiros e tripulação.",
            "Falhas nos sistemas de descarga de resíduos da aeronave, causando problemas de higiene e mau cheiro na cabine.",
            "Problemas nos sistemas de extinção de incêndio da aeronave, dificultando a contenção e controle de incêndios a bordo.",
            "Tentativa de invasão ou sabotagem dos sistemas eletrônicos da aeronave por hackers, representando risco à segurança operacional.",
            "Interrupção ou mau funcionamento dos sistemas de navegação por GPS da aeronave, dificultando a determinação da posição e rota correta."
        ),
        descricaoProblemas(
            "Impacto da aeronave com granizo durante o voo, podendo causar danos à estrutura externa, como rachaduras ou amassados.",
            " Desequilíbrio ou instabilidade na aerodinâmica da aeronave, tornando o voo mais desafiador e potencialmente perigoso.",
            "Problemas nos sistemas que alertam os pilotos sobre a proximidade do solo, aumentando o risco de colisão durante a aproximação para o pouso.",
            "Falhas nos sistemas de comunicação de emergência da aeronave, dificultando o contato com serviços de resgate e assistência em caso de emergência.",
            " Perda de pressão no compartimento de carga da aeronave, afetando a segurança e integridade de carga sensível à pressão, como animais vivos ou mercadorias perecíveis."
        ),
        descricaoProblemas(
            "Problemas nos sistemas que garantem o travamento correto das portas da cabine e compartimentos de carga, representando risco de despressurização e segurança.",
            "Dificuldade em manter uma temperatura confortável e estável na cabine da aeronave, afetando o conforto dos passageiros e tripulação.",
            "Problemas nos sistemas que monitoram e controlam o consumo de combustível da aeronave, podendo resultar em esgotamento de combustível e atrasos.",
            " Situação em que um membro da tripulação requer atenção médica de emergência durante o voo, exigindo avaliação e possíveis medidas de desvio ou pouso de emergência.",
            "Falhas nos sistemas de ventilação da cabine, afetando a qualidade do ar e a circulação de oxigênio, podendo causar desconforto e problemas de saúde aos passageiros."
        ),
        descricaoProblemas(
            "Problemas nos sistemas que permitem a descarga controlada de combustível da aeronave em caso de emergência, afetando a capacidade de aliviar peso antes do pouso.",
            " Interrupção ou falha na comunicação por rádio com outras aeronaves nas proximidades, aumentando o risco de colisões e interferindo na segurança do voo.",
            "Falhas nos sistemas que ativam automaticamente os coletes salva-vidas em caso de pouso de emergência sobre água, comprometendo a segurança dos passageiros em caso de evacuação.",
            " Problemas nos sistemas que fornecem iluminação de emergência na cabine e corredores da aeronave em caso de apagão, dificultando a evacuação e orientação dos passageiros.",
            "Interferência externa nos sistemas de comando de voo da aeronave, como por exemplo, por interferência eletromagnética, comprometendo a capacidade de controle e segurança operacional da aeronave."
        ),
        descricaoProblemas(
            "Perda da pressão interna da aeronave, o que pode levar à falta de oxigênio e problemas respiratórios para os passageiros e tripulação.",
            "Um dos motores da aeronave para de funcionar, podendo exigir procedimentos de emergência, como um pouso de emergência.",
            "Movimentos bruscos e imprevisíveis da aeronave devido a correntes de ar instáveis, causando desconforto e potencialmente ferimentos aos passageiros.",
            "Formação de gelo nos instrumentos de voo, dificultando a leitura e interferindo na capacidade dos pilotos de monitorar e controlar a aeronave.",
            "Falha nos sistemas elétricos da aeronave, afetando sistemas essenciais como comunicações, navegação e iluminação."
        ),
        descricaoProblemas(
            "Ocorrência de fogo dentro da aeronave, exigindo ação rápida da tripulação para extinguir o fogo e garantir a segurança dos passageiros.",
            "Problemas nos sistemas que controlam a altitude, direção e estabilidade da aeronave, podendo levar a uma perda de controle.",
            "Escape de combustível da aeronave, representando um risco de incêndio ou perda de potência dos motores.",
            "Impacto da aeronave com aves durante o voo, podendo danificar os motores ou outras partes da aeronave.",
            "Dificuldades na transmissão ou recepção de mensagens entre a aeronave e os controladores de tráfego aéreo, afetando a segurança e eficiência do voo."
        ),
        descricaoProblemas(
            "Situação em que os pilotos perdem a noção correta da posição, atitude e movimento da aeronave, geralmente devido a condições meteorológicas adversas.",
            "Emergências médicas a bordo, como ataques cardíacos, reações alérgicas ou desmaios, exigindo atenção imediata da tripulação.",
            "Deterioração da estrutura externa da aeronave devido a fatores como corrosão, fadiga ou danos mecânicos.",
            " Acúmulo de gelo nas asas da aeronave, prejudicando sua capacidade de sustentação e aerodinâmica.",
            "Interrupção ou falha nos sistemas de navegação da aeronave, dificultando a determinação da posição e rota correta."
        ),
        descricaoProblemas(
            "Falhas nos sistemas que mantêm a pressão da cabine adequada, podendo causar desconforto ou problemas de saúde nos passageiros.",
            " Presença de fumaça no interior da aeronave, indicando a possibilidade de incêndio ou mau funcionamento dos sistemas de ventilação.",
            "Problemas com os mecanismos que baixam ou travam o trem de pouso da aeronave, exigindo procedimentos especiais durante o pouso.",
            " Problemas nos sistemas de armazenamento e distribuição de combustível da aeronave, podendo levar a perda de potência dos motores. ",
            " Alteração na rota planejada devido a restrições ou fechamento de áreas do espaço aéreo, causando atrasos ou aumento do consumo de combustível."
        ),
        descricaoProblemas(
            "Falhas nos sistemas de fornecimento de oxigênio de emergência, comprometendo a segurança dos passageiros em caso de despressurização.",
            "Problemas nos sistemas de controle de temperatura e umidade da cabine, afetando o conforto dos passageiros.",
            "Solução ou queda de carga da aeronave durante o voo, representando perigo para a segurança da aeronave e pessoas em terra.",
            "Desvio da rota planejada devido a erros de navegação ou cálculo incorreto das condições meteorológicas, podendo aumentar o tempo de voo e o consumo de combustível.",
            "Tentativa de sabotagem, ataque terrorista ou sequestro da aeronave, representando grave ameaça à segurança dos passageiros e tripulação."
        ),
        descricaoProblemas(
            "Falhas nos sistemas de entretenimento a bordo, causando desconforto e frustração entre os passageiros.",
            " Colisão da aeronave com drones não autorizados, podendo danificar os motores ou outras partes da aeronave.",
            "Problemas nos sistemas de freios da aeronave, tornando o pouso e a desaceleração mais desafiadores e potencialmente perigosos.",
            "Problemas nos sistemas de iluminação da cabine ou pista de pouso, afetando a visibilidade e a segurança durante o voo e pouso.",
            "Interrupção ou falha nos sistemas de comunicação interna da aeronave, dificultando a coordenação entre a tripulação."
        ),
        descricaoProblemas(
            "Presença de animais a bordo da aeronave, como roedores, representando riscos à saúde e segurança dos passageiros e tripulação.",
            "Falhas nos sistemas de descarga de resíduos da aeronave, causando problemas de higiene e mau cheiro na cabine.",
            "Problemas nos sistemas de extinção de incêndio da aeronave, dificultando a contenção e controle de incêndios a bordo.",
            "Tentativa de invasão ou sabotagem dos sistemas eletrônicos da aeronave por hackers, representando risco à segurança operacional.",
            "Interrupção ou mau funcionamento dos sistemas de navegação por GPS da aeronave, dificultando a determinação da posição e rota correta."
        ),
        descricaoProblemas(
            "Impacto da aeronave com granizo durante o voo, podendo causar danos à estrutura externa, como rachaduras ou amassados.",
            " Desequilíbrio ou instabilidade na aerodinâmica da aeronave, tornando o voo mais desafiador e potencialmente perigoso.",
            "Problemas nos sistemas que alertam os pilotos sobre a proximidade do solo, aumentando o risco de colisão durante a aproximação para o pouso.",
            "Falhas nos sistemas de comunicação de emergência da aeronave, dificultando o contato com serviços de resgate e assistência em caso de emergência.",
            " Perda de pressão no compartimento de carga da aeronave, afetando a segurança e integridade de carga sensível à pressão, como animais vivos ou mercadorias perecíveis."
        ),
        descricaoProblemas(
            "Problemas nos sistemas que garantem o travamento correto das portas da cabine e compartimentos de carga, representando risco de despressurização e segurança.",
            "Dificuldade em manter uma temperatura confortável e estável na cabine da aeronave, afetando o conforto dos passageiros e tripulação.",
            "Problemas nos sistemas que monitoram e controlam o consumo de combustível da aeronave, podendo resultar em esgotamento de combustível e atrasos.",
            " Situação em que um membro da tripulação requer atenção médica de emergência durante o voo, exigindo avaliação e possíveis medidas de desvio ou pouso de emergência.",
            "Falhas nos sistemas de ventilação da cabine, afetando a qualidade do ar e a circulação de oxigênio, podendo causar desconforto e problemas de saúde aos passageiros."
        ),
        descricaoProblemas(
            "Problemas nos sistemas que permitem a descarga controlada de combustível da aeronave em caso de emergência, afetando a capacidade de aliviar peso antes do pouso.",
            " Interrupção ou falha na comunicação por rádio com outras aeronaves nas proximidades, aumentando o risco de colisões e interferindo na segurança do voo.",
            "Falhas nos sistemas que ativam automaticamente os coletes salva-vidas em caso de pouso de emergência sobre água, comprometendo a segurança dos passageiros em caso de evacuação.",
            " Problemas nos sistemas que fornecem iluminação de emergência na cabine e corredores da aeronave em caso de apagão, dificultando a evacuação e orientação dos passageiros.",
            "Interferência externa nos sistemas de comando de voo da aeronave, como por exemplo, por interferência eletromagnética, comprometendo a capacidade de controle e segurança operacional da aeronave."
        ),
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_problemas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        saidaV = findViewById(R.id.textSaidaProblema)
        chegadaV = findViewById(R.id.textoChegadaProblema)
        valorV = findViewById(R.id.textValorProblema)
        problemaB1 = findViewById(R.id.problemaB1)
        problemaB2 = findViewById(R.id.problemaB2)
        problemaB3 = findViewById(R.id.problemaB3)
        problemaB4 = findViewById(R.id.problemaB4)
        problemaB5 = findViewById(R.id.problemaB5)
        descricao = findViewById(R.id.descricao)
        buttonVoltar = findViewById(R.id.buttonVoltar)

        //recebendo os dados de MainActivity
        val dados: Bundle? = intent.extras
        val saida = dados?.getString("saida")
        val chegada = dados?.getString("chegada")
        val valor = dados?.getString("valor")
        contador = dados?.getInt("contador")!!

        saidaV.text = saida
        chegadaV.text = chegada
        valorV.text = valor
        changePerguntas()

        problemaB1.setOnClickListener { problemaSelecionado = 0; changeDescricao() }
        problemaB2.setOnClickListener { problemaSelecionado = 1; changeDescricao() }
        problemaB3.setOnClickListener { problemaSelecionado = 2; changeDescricao() }
        problemaB4.setOnClickListener { problemaSelecionado = 3; changeDescricao() }
        problemaB5.setOnClickListener { problemaSelecionado = 4; changeDescricao() }

        buttonVoltar.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun changePerguntas() {
        val probAtual = problemas[contador]
        when (contador) {
            0 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            1 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            2 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            3 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            4 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            5 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            6 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            7 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            8 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            9 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            10 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            11 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            12 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            13 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            14 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            15 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            16 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            17 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            18 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }

            19 -> {
                problemaB1.text = probAtual.texto1
                problemaB2.text = probAtual.texto2
                problemaB3.text = probAtual.texto3
                problemaB4.text = probAtual.texto4
                problemaB5.text = probAtual.texto5
            }
        }
    }

    private fun changeDescricao() {
        val descAtual = descricoes[contador]
        when (contador) {
            0 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            1 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            2 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            3 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            4 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            5 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            6 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            7 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            8 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            9 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            10 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            11 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            12 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            13 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            14 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            15 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            16 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            17 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            18 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }

            19 -> {
                when (problemaSelecionado) {
                    0 -> {
                        descricao.text = descAtual.descricao1
                    }

                    1 -> {
                        descricao.text = descAtual.descricao2
                    }

                    2 -> {
                        descricao.text = descAtual.descricao3
                    }

                    3 -> {
                        descricao.text = descAtual.descricao4
                    }

                    4 -> {
                        descricao.text = descAtual.descricao5
                    }
                }
            }
        }
    }
}