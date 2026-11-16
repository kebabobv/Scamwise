package com.miniphone.scamwise;



// Importiamo le classi necessarie
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    // Variabile che indica quale domanda stiamo mostrando
    int questionIndex = 0;

    // Variabile che contiene il punteggio
    int score = 0;

    // Indica se il test  terminato
    boolean finished = false;

    // Elementi grafici dell'applicazione
    TextView scoreText;
    TextView questionNumber;
    TextView categoryText;
    TextView senderText;
    TextView messageText;
    TextView linkText;
    TextView questionText;
    TextView feedbackText;

    Button scamButton;
    Button safeButton;
    Button nextButton;


    // METODI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Colleghiamo il file XML a questa Activity
        setContentView(R.layout.activity_main);

        // Colleghiamo le variabili Java agli elementi XML
        scoreText = findViewById(R.id.scoreText);
        questionNumber = findViewById(R.id.questionNumber);
        categoryText = findViewById(R.id.categoryText);
        senderText = findViewById(R.id.senderText);
        messageText = findViewById(R.id.messageText);
        linkText = findViewById(R.id.linkText);
        questionText = findViewById(R.id.questionText);
        feedbackText = findViewById(R.id.feedbackText);

        scamButton = findViewById(R.id.scamButton);
        safeButton = findViewById(R.id.safeButton);
        nextButton = findViewById(R.id.nextButton);

        // Mostriamo la prima domanda
        displayQuestion();
    }


    // mostro le domande
    public void displayQuestion() {

        // Aggiorniamo il punteggio
        scoreText.setText("IL TUO PUNTEGGIO: " + (score * 100) + " XP");

        // Mostriamo il numero della domanda
        questionNumber.setText(
                "MISSIONE " + (questionIndex + 1) + " / 03"
        );

        // Nascondiamo la spiegazione precedente
        feedbackText.setVisibility(View.GONE);

        // Nascondiamo il pulsante della domanda successiva
        nextButton.setVisibility(View.GONE);

        // Riattiviamo i pulsanti delle risposte
        scamButton.setEnabled(true);
        safeButton.setEnabled(true);

        // Impostiamo il testo della domanda
        questionText.setText("Questo messaggio è una truffa?");


        // DOMANDA 1
        if (questionIndex == 0) {

            categoryText.setText("PHISHING");

            senderText.setText("La tua banca");

            messageText.setText(
                    "Il tuo account è stato bloccato! " +
                            "Verifica immediatamente la tua identità " +
                            "per ripristinare l'accesso."
            );

            linkText.setText(
                    "suspicious-link.example.test"
            );

        }

        // DOMANDA 2
        else if (questionIndex == 1) {

            categoryText.setText("AVVISO UFFICIALE");

            senderText.setText("Biblioteca scolastica");

            messageText.setText(
                    "Cari studenti, la biblioteca scolastica " +
                            "sarà chiusa domani. " +
                            "Consultate l'avviso ufficiale della scuola " +
                            "per maggiori informazioni."
            );

            linkText.setText(
                    "Nessun link esterno"
            );

        }

        // DOMANDA 3
        else if (questionIndex == 2) {

            categoryText.setText("FALSO PREMIO");

            senderText.setText("Congratulazioni!");

            messageText.setText(
                    "Hai vinto uno smartphone gratis! " +
                            "Paga una piccola commissione e " +
                            "invia i tuoi dati personali " +
                            "per ricevere il premio."
            );

            linkText.setText(
                    "claim-your-prize.example.test"
            );

        }
    }


    // quando utente preme its a scam
    public void onScamClick(View view) {

        // Inviamo true perché l'utente ha scelto TRUFFA
        checkAnswer(true);
    }


    // quando é safe

    public void onSafeClick(View view) {

        // Inviamo false perché l'utente ha scelto SICURO
        checkAnswer(false);
    }


    // CONTROLLO DELLA RISPOSTA


    public void checkAnswer(boolean userAnswer) {

        // Variabile che contiene la risposta corretta
        boolean correctAnswer;

        // Variabile che contiene la spiegazione
        String explanation;


        // Controlliamo la prima domanda
        if (questionIndex == 0) {

            correctAnswer = true;

            explanation =
                    "Questo è un tentativo di phishing! Il messaggio " +
                            "crea un senso di urgenza e ti chiede di " +
                            "verificare la tua identità tramite un " +
                            "link sospetto.";

        }

        // Controlliamo la seconda domanda
        else if (questionIndex == 1) {

            correctAnswer = false;

            explanation =
                    "Questo sembra un normale " +
                            "avviso scolastico. Tuttavia, dovresti " +
                            "sempre verificare i messaggi importanti " +
                            "attraverso i canali ufficiali della scuola.";

        }

        // Controlliamo la terza domanda
        else {

            correctAnswer = true;

            explanation =
                    "Questa è una truffa basata su un falso premio! " +
                            "Ti chiede dati personali " +
                            "e denaro per ricevere un premio.";

        }


        // Confrontiamo la risposta dell'utente
        // con quella corretta

        if (userAnswer == correctAnswer) {

            // Aumentiamo il punteggio
            score = score + 1;

            // Mostriamo un messaggio positivo
            feedbackText.setText(
                    "CORRETTO! +100 XP\n\n" + explanation
            );

        } else {

            // Mostriamo un messaggio in caso di errore
            feedbackText.setText(
                    "SBAGLIATO!\n\n" + explanation
            );
        }


        // Aggiorniamo il punteggio sullo schermo
        scoreText.setText("IL TUO PUNTEGGIO: " + (score * 100) + " XP");

        // Rendiamo visibile la spiegazione
        feedbackText.setVisibility(View.VISIBLE);

        // Disattiviamo i pulsanti per evitare
        // che l'utente risponda più volte
        scamButton.setEnabled(false);
        safeButton.setEnabled(false);

        // Mostriamo il pulsante successivo
        nextButton.setVisibility(View.VISIBLE);

        // Cambiamo il testo dell'ultimo pulsante
        if (questionIndex == 2) {

            nextButton.setText("VEDI RISULTATI");

        } else {

            nextButton.setText("DOMANDA SUCCESSIVA");
        }
    }


    // passa alla domanda successiva

    public void onNextClick(View view) {

        // Se il test è terminato, ricominciamo
        if (finished == true) {

            restartQuiz();

        }

        // Se non siamo all'ultima domanda
        else if (questionIndex < 2) {

            // Passiamo alla domanda successiva
            questionIndex = questionIndex + 1;

            // Aggiorniamo la schermata
            displayQuestion();

        }

        // Se abbiamo completato tutte le domande
        else {

            showFinalResult();
        }
    }


    // risultato finale
    public void showFinalResult() {

        // Indichiamo che il test è terminato
        finished = true;

        // Nascondiamo i pulsanti delle risposte
        scamButton.setVisibility(View.GONE);
        safeButton.setVisibility(View.GONE);

        // Aggiorniamo il titolo
        questionNumber.setText("FORMAZIONE COMPLETATA");

        categoryText.setText("I TUOI RISULTATI");

        senderText.setText("SCAMWISE");

        messageText.setText(
                "Hai completato il tuo percorso di formazione sulla sicurezza!"
        );

        linkText.setText("");

        questionText.setText(
                "Il tuo punteggio finale: " + score + " / 3"
        );

        // Mostriamo il risultato
        feedbackText.setText(
                "Hai guadagnato " + (score * 100) + " XP!\n\n" +
                        "Continua a imparare come proteggerti online."
        );

        feedbackText.setVisibility(View.VISIBLE);

        // Cambiamo il pulsante in RESTART
        nextButton.setText("GIOCA ANCORA");
    }


    // ricominicia il test
    public void restartQuiz() {

        // Azzeriamo il punteggio
        score = 0;

        // Torniamo alla prima domanda
        questionIndex = 0;

        // Il test non è più terminato
        finished = false;

        // Riattiviamo i pulsanti
        scamButton.setVisibility(View.VISIBLE);
        safeButton.setVisibility(View.VISIBLE);

        // Mostriamo nuovamente la prima domanda
        displayQuestion();
    }

}
