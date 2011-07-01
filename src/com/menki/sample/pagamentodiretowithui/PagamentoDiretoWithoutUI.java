package com.menki.sample.pagamentodiretowithui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.menki.moip.paymentmethods.OnPaymentListener;
import com.menki.moip.paymentmethods.PagamentoDireto;
import com.menki.moip.paymentmethods.PagamentoDireto.OwnerIdType;
import com.menki.moip.utils.Config.RemoteServer;
import com.menki.moip.utils.MoIPResponse;

public class PagamentoDiretoWithoutUI extends Activity implements OnPaymentListener {
	private PagamentoDireto pagDir;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		pagDir = new PagamentoDireto();
		pagDir.setOnPaymentListener(this);
		pagDir.setServerType(RemoteServer.TEST);
		pagDir.setToken("SEU TOKEN DO MOIP");
		pagDir.setKey("SUA KEY DO MOIP");
        pagDir.setAddressComplement("x");
        pagDir.setBrand("Visa");
        pagDir.setCity("Sucupira");
        pagDir.setCountry("BRA");
        pagDir.setCreditCardNumber("3456789012345640");
        pagDir.setExpirationDate("08/11");
        pagDir.setInstallmentsQuantity("2");
        pagDir.setNeighborhood("Vila Vintem");
        pagDir.setOwnerBirthDate("01/01/1983");
        pagDir.setOwnerIdNumber("111.111.111-11");
        pagDir.setOwnerIdType(OwnerIdType.CPF);
        pagDir.setOwnerName("Lindolfo Pires");
        pagDir.setCellPhone("(11)1111-1111");
        pagDir.setFixedPhone("111.111.111-11");
        pagDir.setSecureCode("101");
        pagDir.setServerType(RemoteServer.TEST);
        pagDir.setState("AC");
        pagDir.setStreetAddress("Avenida Brasil");
        pagDir.setStreetNumberAddress("100");
        pagDir.setValue("213.25");
        pagDir.setZipCode("10100-100");
        
        ((Button) findViewById(R.id.pay)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				(new PayTask()).execute();
			}
		});
	}

	@Override
	public void onPaymentSuccess(MoIPResponse response) {
		Log.d("MOIP", "Sucesso");
		Toast.makeText(this, "SUCESS!", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onPaymentFail(MoIPResponse response) {
		Log.d("MOIP", "Falha");
		Toast.makeText(this, "FAIL!", Toast.LENGTH_LONG).show();		
	}
	
    private class PayTask extends AsyncTask<String, Void, Void>{
		private final ProgressDialog dialog = new ProgressDialog(PagamentoDiretoWithoutUI.this);
		
		protected void onPreExecute(){
			dialog.setMessage("Processing...");
			dialog.show();
		}
		
		@Override
		protected Void doInBackground(String... params) {
			pagDir.pay();
			
			return null;
		}
		
		protected void onPostExecute(final Void unused){
			if(dialog.isShowing())
				dialog.dismiss();
		}
	}	
}