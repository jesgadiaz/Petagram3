package org.coursera.petagram2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by rodomualdo on 26/05/2016.
 */
public class MailActivity extends AppCompatActivity {

    Session session;
    Button btnSend;
    Toolbar toolbar;
    TextInputLayout tilTo, tilSubject, tilMessage, tilFrom, tilPassword;
    String to, subject, message, from, password;
    ProgressDialog pdialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        // ActionBar
        toolbar = (Toolbar) findViewById(R.id.myActionBar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tilTo = (TextInputLayout) findViewById(R.id.tilTo);
        tilSubject = (TextInputLayout) findViewById(R.id.tilSubject);
        tilMessage = (TextInputLayout) findViewById(R.id.tilMessage);
        tilFrom = (TextInputLayout) findViewById(R.id.tilFrom);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                to = tilTo.getEditText().getText().toString();
                from = tilFrom.getEditText().getText().toString();
                password = tilPassword.getEditText().getText().toString();
                subject = tilSubject.getEditText().getText().toString();
                message = tilMessage.getEditText().getText().toString();

                /*Properties properties = new Properties();
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.port", "465");
*/
                Properties props = new Properties();
                props.setProperty("mail.transport.protocol", "smtp");
                props.setProperty("mail.host", "smtp.gmail.com");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");
                props.setProperty("mail.smtp.quitwait", "false");

                session = Session.getDefaultInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

                pdialog = ProgressDialog.show(MailActivity.this, "", "Enviando...", true);

                RetreiveFeedTask task = new RetreiveFeedTask();
                task.execute();
            }
        });
    }

    class RetreiveFeedTask extends AsyncTask<String, String, Void>{

        @Override
        protected Void doInBackground(String... params) {
            try{
                Message m = new MimeMessage(session);
                m.setFrom(new InternetAddress(from));
                m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                m.setSubject(subject);
                m.setText(message);
                //m.setContent(message, "text/html; charset=utf-8");

                Transport.send(m);
            }catch(AddressException e){
                e.printStackTrace();
            }catch(MessagingException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            pdialog.dismiss();
            tilFrom.getEditText().setText("");
            tilPassword.getEditText().setText("");
            tilTo.getEditText().setText("");
            tilSubject.getEditText().setText("");
            tilMessage.getEditText().setText("");

            Toast toast = Toast.makeText(getBaseContext(), "Mensaje enviado", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
