package ani.dantotsu.home

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import ani.dantotsu.R
import ani.dantotsu.connections.anilist.Anilist
import ani.dantotsu.databinding.DialogUserAgentBinding
import ani.dantotsu.databinding.FragmentLoginBinding
import ani.dantotsu.openLinkInBrowser
import ani.dantotsu.settings.saving.internal.PreferenceKeystore
import ani.dantotsu.settings.saving.internal.PreferencePackager
import ani.dantotsu.toast
import ani.dantotsu.util.Logger
import ani.dantotsu.util.customAlertDialog

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginButton.setOnClickListener { Anilist.loginIntent(requireActivity()) }
        binding.loginDiscord.setOnClickListener { openLinkInBrowser(getString(R.string.discord)) }
        binding.loginGithub.setOnClickListener { openLinkInBrowser(getString(R.string.github)) }
        binding.loginTelegram.setOnClickListener { openLinkInBrowser(getString(R.string.telegram)) }

        val openDocumentLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
            uri?.let {
                try {
                    val inputStream = requireActivity().contentResolver.openInputStream(it)
                    val jsonString = inputStream?.readBytes() ?: throw Exception("Error reading file")
                    val name = DocumentFile.fromSingleUri(requireActivity(), it)?.name ?: "settings"
                    
                    when {
                        name.endsWith(".sani") -> passwordAlertDialog { password ->
                            if (password != null) {
                                try {
                                    val salt = jsonString.copyOfRange(0, 16)
                                    val encrypted = jsonString.copyOfRange(16, jsonString.size)
                                    val decryptedJson = PreferenceKeystore.decryptWithPassword(password, encrypted, salt)
                                    if (PreferencePackager.unpack(decryptedJson)) {
                                        restartApp()
                                    }
                                } catch (e: Exception) {
                                    toast(getString(R.string.incorrect_password))
                                }
                            } else {
                                toast(getString(R.string.password_cannot_be_empty))
                            }
                        }
                        name.endsWith(".ani") -> {
                            val decryptedJson = jsonString.toString(Charsets.UTF_8)
                            if (PreferencePackager.unpack(decryptedJson)) {
                                restartApp()
                            }
                        }
                        else -> toast(getString(R.string.unknown_file_type))
                    }
                } catch (e: Exception) {
                    Logger.log(e)
                    toast(getString(R.string.error_importing_settings))
                }
            }
        }

        binding.importSettingsButton.setOnClickListener {
            openDocumentLauncher.launch(arrayOf("*/*"))
        }
    }

    private fun passwordAlertDialog(callback: (CharArray?) -> Unit) {
        val password = CharArray(16).apply { fill('0') }

        val dialogView = DialogUserAgentBinding.inflate(layoutInflater).apply {
            userAgentTextBox.hint = getString(R.string.password)
            subtitle.visibility = View.VISIBLE
            subtitle.text = getString(R.string.enter_password_to_decrypt_file)
            
            val showPasswordToggle = TextView(requireContext()).apply {
                text = "👁️ ${getString(R.string.show_password)}"
                setTextColor(ContextCompat.getColor(requireContext(), R.color.brand))
                setPadding(
                    0, 
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 
                        16f, 
                        resources.displayMetrics
                    ).toInt(), 
                    0, 
                    0
                )
                setOnClickListener {
                    val selection = userAgentTextBox.selectionEnd
                    userAgentTextBox.transformationMethod = if (userAgentTextBox.transformationMethod == PasswordTransformationMethod.getInstance()) {
                        text = "👁️ ${getString(R.string.hide_password)}"
                        null
                    } else {
                        text = "👁️ ${getString(R.string.show_password)}"
                        PasswordTransformationMethod.getInstance()
                    }
                    userAgentTextBox.setSelection(selection)
                }
            }
            userAgentContainer.addView(showPasswordToggle)
        }

        customAlertDialog().apply {
            setTitle(getString(R.string.enter_password))
            setCustomView(dialogView.root)
            setPositiveButton(getString(R.string.ok)) {
                dialogView.userAgentTextBox.text?.toString()?.trim()?.let { text ->
                    if (text.isNotBlank()) {
                        text.toCharArray(password)
                        callback(password)
                    } else {
                        toast(getString(R.string.password_cannot_be_empty))
                    }
                }
            }
            setNegativeButton(getString(R.string.cancel)) {
                password.fill('0')
                callback(null)
            }
            show()
        }
    }

    private fun restartApp() {
        startActivity(Intent(requireActivity(), requireActivity().javaClass).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        requireActivity().finish()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}