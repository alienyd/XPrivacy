package biz.bokhorst.xprivacy;

import java.security.InvalidParameterException;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsDialog {

	public static void edit(final Context context, ApplicationInfoEx appInfo) {
		final int uid = (appInfo == null ? 0 : appInfo.getUid());

		// Build dialog
		final Dialog dlgSettings = new Dialog(context);
		dlgSettings.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		dlgSettings.setTitle(context.getString(R.string.app_name));
		dlgSettings.setContentView(R.layout.settings);
		dlgSettings.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, getThemed(context, R.attr.icon_launcher));

		// Reference controls
		TextView tvAppName = (TextView) dlgSettings.findViewById(R.id.tvAppName);
		final EditText etSerial = (EditText) dlgSettings.findViewById(R.id.etSerial);
		final EditText etLat = (EditText) dlgSettings.findViewById(R.id.etLat);
		final EditText etLon = (EditText) dlgSettings.findViewById(R.id.etLon);
		final EditText etSearch = (EditText) dlgSettings.findViewById(R.id.etSearch);
		Button btnSearch = (Button) dlgSettings.findViewById(R.id.btnSearch);
		final EditText etMac = (EditText) dlgSettings.findViewById(R.id.etMac);
		final EditText etIP = (EditText) dlgSettings.findViewById(R.id.etIP);
		final EditText etImei = (EditText) dlgSettings.findViewById(R.id.etImei);
		final EditText etPhone = (EditText) dlgSettings.findViewById(R.id.etPhone);
		final EditText etId = (EditText) dlgSettings.findViewById(R.id.etId);
		final EditText etGsfId = (EditText) dlgSettings.findViewById(R.id.etGsfId);
		final EditText etMcc = (EditText) dlgSettings.findViewById(R.id.etMcc);
		final EditText etMnc = (EditText) dlgSettings.findViewById(R.id.etMnc);
		final EditText etCountry = (EditText) dlgSettings.findViewById(R.id.etCountry);
		final EditText etOperator = (EditText) dlgSettings.findViewById(R.id.etOperator);
		final EditText etIccId = (EditText) dlgSettings.findViewById(R.id.etIccId);
		final EditText etSubscriber = (EditText) dlgSettings.findViewById(R.id.etSubscriber);
		final EditText etUa = (EditText) dlgSettings.findViewById(R.id.etUa);
		final CheckBox cbUsage = (CheckBox) dlgSettings.findViewById(R.id.cbUsage);
		final CheckBox cbLog = (CheckBox) dlgSettings.findViewById(R.id.cbLog);
		final Button btnRandom = (Button) dlgSettings.findViewById(R.id.btnRandom);
		final CheckBox cbRandom = (CheckBox) dlgSettings.findViewById(R.id.cbRandom);

		final CheckBox cbSerial = (CheckBox) dlgSettings.findViewById(R.id.cbSerial);
		final CheckBox cbLat = (CheckBox) dlgSettings.findViewById(R.id.cbLat);
		final CheckBox cbLon = (CheckBox) dlgSettings.findViewById(R.id.cbLon);
		final CheckBox cbMac = (CheckBox) dlgSettings.findViewById(R.id.cbMac);
		final CheckBox cbImei = (CheckBox) dlgSettings.findViewById(R.id.cbImei);
		final CheckBox cbPhone = (CheckBox) dlgSettings.findViewById(R.id.cbPhone);
		final CheckBox cbId = (CheckBox) dlgSettings.findViewById(R.id.cbId);
		final CheckBox cbGsfId = (CheckBox) dlgSettings.findViewById(R.id.cbGsfId);
		final CheckBox cbCountry = (CheckBox) dlgSettings.findViewById(R.id.cbCountry);

		Button btnOk = (Button) dlgSettings.findViewById(R.id.btnOk);
		Button btnClear = (Button) dlgSettings.findViewById(R.id.btnClear);

		// Display app name
		if (appInfo == null)
			tvAppName.setVisibility(View.GONE);
		else
			tvAppName.setText(appInfo.getFirstApplicationName());

		// Set current values
		boolean usage = PrivacyManager.getSettingBool(null, context, uid, PrivacyManager.cSettingAndroidUsage, false,
				false);
		boolean log = PrivacyManager.getSettingBool(null, context, uid, PrivacyManager.cSettingLog, false, false);
		boolean random = PrivacyManager.getSettingBool(null, context, uid, PrivacyManager.cSettingRandom, false, false);

		String serial = PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingSerial, "", false);
		String lat = PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingLatitude, "", false);
		String lon = PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingLongitude, "", false);
		String mac = PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingMac, "", false);
		String imei = PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingImei, "", false);
		String phone = PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingPhone, "", false);
		String id = PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingId, "", false);
		String gsfid = PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingGsfId, "", false);
		String country = PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingCountry, "", false);

		cbSerial.setChecked(serial.equals(PrivacyManager.cValueRandom));
		cbLat.setChecked(lat.equals(PrivacyManager.cValueRandom));
		cbLon.setChecked(lon.equals(PrivacyManager.cValueRandom));
		cbMac.setChecked(mac.equals(PrivacyManager.cValueRandom));
		cbImei.setChecked(imei.equals(PrivacyManager.cValueRandom));
		cbPhone.setChecked(phone.equals(PrivacyManager.cValueRandom));
		cbId.setChecked(id.equals(PrivacyManager.cValueRandom));
		cbGsfId.setChecked(gsfid.equals(PrivacyManager.cValueRandom));
		cbCountry.setChecked(country.equals(PrivacyManager.cValueRandom));

		etSerial.setText(cbSerial.isChecked() ? "" : serial);
		etLat.setText(cbLat.isChecked() ? "" : lat);
		etLon.setText(cbLon.isChecked() ? "" : lon);
		etMac.setText(cbMac.isChecked() ? "" : mac);
		etImei.setText(cbImei.isChecked() ? "" : imei);
		etPhone.setText(cbPhone.isChecked() ? "" : phone);
		etId.setText(cbId.isChecked() ? "" : id);
		etGsfId.setText(cbGsfId.isChecked() ? "" : gsfid);
		etCountry.setText(cbCountry.isChecked() ? "" : country);

		etSerial.setEnabled(!cbSerial.isChecked());
		etLat.setEnabled(!cbLat.isChecked());
		etLon.setEnabled(!cbLon.isChecked());
		etMac.setEnabled(!cbMac.isChecked());
		etImei.setEnabled(!cbImei.isChecked());
		etPhone.setEnabled(!cbPhone.isChecked());
		etId.setEnabled(!cbId.isChecked());
		etGsfId.setEnabled(!cbGsfId.isChecked());
		etCountry.setEnabled(!cbCountry.isChecked());

		cbSerial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				etSerial.setEnabled(!isChecked);
			}
		});
		cbLat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				etLat.setEnabled(!isChecked);
			}
		});
		cbLon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				etLon.setEnabled(!isChecked);
			}
		});
		cbMac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				etMac.setEnabled(!isChecked);
			}
		});
		cbImei.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				etImei.setEnabled(!isChecked);
			}
		});
		cbPhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				etPhone.setEnabled(!isChecked);
			}
		});
		cbId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				etId.setEnabled(!isChecked);
			}
		});
		cbGsfId.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				etGsfId.setEnabled(!isChecked);
			}
		});
		cbCountry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				etCountry.setEnabled(!isChecked);
			}
		});

		etIP.setText(PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingIP, "", false));
		etMcc.setText(PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingMcc, "", false));
		etMnc.setText(PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingMnc, "", false));
		etOperator.setText(PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingOperator, "", false));
		etIccId.setText(PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingIccId, "", false));
		etSubscriber.setText(PrivacyManager
				.getSetting(null, context, uid, PrivacyManager.cSettingSubscriber, "", false));
		etUa.setText(PrivacyManager.getSetting(null, context, uid, PrivacyManager.cSettingUa, "", false));

		if (uid == 0) {
			cbUsage.setChecked(usage);
			cbLog.setChecked(log);
		} else {
			cbUsage.setVisibility(View.GONE);
			cbLog.setVisibility(View.GONE);
		}
		cbRandom.setChecked(random);

		// Handle search
		etSearch.setEnabled(Geocoder.isPresent());
		btnSearch.setEnabled(Geocoder.isPresent());
		btnSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					etLat.setText("");
					etLon.setText("");
					String search = etSearch.getText().toString();
					final List<Address> listAddress = new Geocoder(context).getFromLocationName(search, 1);
					if (listAddress.size() > 0) {
						Address address = listAddress.get(0);

						// Get coordinates
						if (address.hasLatitude())
							etLat.setText(Double.toString(address.getLatitude()));
						if (address.hasLongitude())
							etLon.setText(Double.toString(address.getLongitude()));

						// Get address
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
							if (i != 0)
								sb.append(", ");
							sb.append(address.getAddressLine(i));
						}
						etSearch.setText(sb.toString());
					}
				} catch (Throwable ex) {
					Util.bug(null, ex);
				}
			}
		});

		// Handle randomize
		btnRandom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				etSerial.setText(PrivacyManager.getRandomProp("SERIAL"));
				etLat.setText(PrivacyManager.getRandomProp("LAT"));
				etLon.setText(PrivacyManager.getRandomProp("LON"));
				etMac.setText(PrivacyManager.getRandomProp("MAC"));
				etImei.setText(PrivacyManager.getRandomProp("IMEI"));
				etPhone.setText(PrivacyManager.getRandomProp("PHONE"));
				etId.setText(PrivacyManager.getRandomProp("ANDROID_ID"));
				etGsfId.setText(PrivacyManager.getRandomProp("GSF_ID"));
				etCountry.setText(PrivacyManager.getRandomProp("ISO3166"));
			}
		});

		// Handle OK
		btnOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// @formatter:off
				if (!cbSerial.isChecked() && etSerial.getText().toString().equals("") &&
					!cbLat.isChecked() && etLat.getText().toString().equals("") &&
					!cbLon.isChecked() && etLon.getText().toString().equals("") &&
					!cbMac.isChecked() && etMac.getText().toString().equals("") &&
					etIP.getText().toString().equals("") &&
					!cbImei.isChecked() && etImei.getText().toString().equals("") &&
					!cbPhone.isChecked() && etPhone.getText().toString().equals("") &&
					!cbId.isChecked() && etId.getText().toString().equals("") &&
					!cbGsfId.isChecked() && etGsfId.getText().toString().equals("") &&
					etMcc.getText().toString().equals("") &&
					etMnc.getText().toString().equals("") &&
					!cbCountry.isChecked() && etCountry.getText().toString().equals("") &&
					etOperator.getText().toString().equals("") &&
					etIccId.getText().toString().equals("") &&
					etSubscriber.getText().toString().equals("") &&
					etUa.getText().toString().equals("")) {
					// @formatter:on

					// Clear all settings
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingSerial, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingLatitude, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingLongitude, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingMac, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingIP, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingImei, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingPhone, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingId, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingGsfId, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingMcc, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingMnc, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingCountry, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingOperator, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingIccId, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingSubscriber, null);
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingUa, null);
					Util.log(null, Log.WARN, "Cleared all settings uid=" + uid);
				} else {
					// Serial#
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingSerial,
							cbSerial.isChecked() ? PrivacyManager.cValueRandom : etSerial.getText().toString());

					// Set latitude
					try {
						float lat = Float.parseFloat(etLat.getText().toString().replace(',', '.'));
						if (lat < -90 || lat > 90)
							throw new InvalidParameterException();
						PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingLatitude,
								cbLat.isChecked() ? PrivacyManager.cValueRandom : Float.toString(lat));
					} catch (Throwable ex) {
						PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingLatitude,
								cbLat.isChecked() ? PrivacyManager.cValueRandom : "");
					}

					// Set longitude
					try {
						float lon = Float.parseFloat(etLon.getText().toString().replace(',', '.'));
						if (lon < -180 || lon > 180)
							throw new InvalidParameterException();
						PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingLongitude,
								cbLon.isChecked() ? PrivacyManager.cValueRandom : Float.toString(lon));
					} catch (Throwable ex) {
						PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingLongitude,
								cbLon.isChecked() ? PrivacyManager.cValueRandom : "");
					}

					// Other settings
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingMac,
							cbMac.isChecked() ? PrivacyManager.cValueRandom : etMac.getText().toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingIP, etIP.getText().toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingImei,
							cbImei.isChecked() ? PrivacyManager.cValueRandom : etImei.getText().toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingPhone,
							cbPhone.isChecked() ? PrivacyManager.cValueRandom : etPhone.getText().toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingId,
							cbId.isChecked() ? PrivacyManager.cValueRandom : etId.getText().toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingGsfId,
							cbGsfId.isChecked() ? PrivacyManager.cValueRandom : etGsfId.getText().toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingMcc, etMcc.getText()
							.toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingMnc, etMnc.getText()
							.toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingCountry,
							cbCountry.isChecked() ? PrivacyManager.cValueRandom : etCountry.getText().toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingOperator, etOperator.getText()
							.toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingIccId, etIccId.getText()
							.toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingSubscriber, etSubscriber
							.getText().toString());
					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingUa, etUa.getText().toString());

					if (uid == 0) {
						PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingAndroidUsage,
								Boolean.toString(cbUsage.isChecked()));
						PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingLog,
								Boolean.toString(cbLog.isChecked()));
					}

					PrivacyManager.setSetting(null, context, uid, PrivacyManager.cSettingRandom,
							Boolean.toString(cbRandom.isChecked()));
				}

				// Done
				dlgSettings.dismiss();
			}
		});

		// Handle clear
		btnClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				etSerial.setText("");
				etLat.setText("");
				etLon.setText("");
				etSearch.setText("");
				etMac.setText("");
				etIP.setText("");
				etImei.setText("");
				etPhone.setText("");
				etId.setText("");
				etGsfId.setText("");
				etMcc.setText("");
				etMnc.setText("");
				etCountry.setText("");
				etOperator.setText("");
				etIccId.setText("");
				etSubscriber.setText("");
				etUa.setText("");
				if (uid == 0) {
					cbUsage.setChecked(false);
					cbLog.setChecked(false);
				}
				cbRandom.setChecked(false);

				cbSerial.setChecked(false);
				cbLat.setChecked(false);
				cbLon.setChecked(false);
				cbMac.setChecked(false);
				cbImei.setChecked(false);
				cbPhone.setChecked(false);
				cbId.setChecked(false);
				cbGsfId.setChecked(false);
				cbCountry.setChecked(false);
			}
		});

		dlgSettings.setCancelable(true);
		dlgSettings.show();
	}

	private static int getThemed(Context context, int attr) {
		TypedValue typedvalueattr = new TypedValue();
		context.getTheme().resolveAttribute(attr, typedvalueattr, true);
		return typedvalueattr.resourceId;
	}
}
