/*
 * This file is part of Chiaki.
 *
 * Chiaki is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Chiaki is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Chiaki.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.metallic.chiaki.settings

import android.content.res.Resources
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.metallic.chiaki.R
import com.metallic.chiaki.common.AppDatabase
import com.metallic.chiaki.common.ext.viewModelFactory
import com.metallic.chiaki.common.getDatabase

class SettingsFragment: PreferenceFragmentCompat(), TitleFragment
{
	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?)
	{
		setPreferencesFromResource(R.xml.preferences, rootKey)

		val registeredHostsPreference = preferenceScreen.findPreference<Preference>("registered_hosts")

		val viewModel = ViewModelProviders
			.of(this, viewModelFactory { SettingsViewModel(getDatabase(context!!)) })
			.get(SettingsViewModel::class.java)

		viewModel.registeredHostsCount.observe(this, Observer {
			registeredHostsPreference?.summary = getString(R.string.preferences_registered_hosts_summary, it)
		})
	}

	override fun getTitle(resources: Resources): String = resources.getString(R.string.title_settings)
}