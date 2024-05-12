package com.example.menetrendek.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.menetrendek.LogIn
import com.example.menetrendek.MainPageActivity
import com.example.menetrendek.R
import com.example.menetrendek.databinding.FragmentAccountBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {
    private lateinit var edtNewpass: EditText
    private lateinit var edtNewpassAgain: EditText
    private lateinit var edtOldPass: EditText
    private lateinit var btnModifyPass: Button
    private lateinit var btnDeleteAcc: ImageButton

    private var _binding: FragmentAccountBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtNewpass = view.findViewById(R.id.new_pass)
        edtNewpassAgain = view.findViewById(R.id.new_pass_again)
        edtOldPass = view.findViewById(R.id.old_pass)
        btnModifyPass = view.findViewById(R.id.modify_pass)

        btnModifyPass.setOnClickListener {
            if (edtNewpass != edtNewpassAgain) {
                changePassword(
                    edtOldPass.text.toString(),
                    edtNewpass.text.toString()
                ) { isSuccessfull, errorMessage ->
                    if (isSuccessfull) {
                        val intent = Intent(activity, MainPageActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    } else {
                        Toast.makeText(
                            activity,
                            errorMessage,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

                }
            } else {
                Toast.makeText(
                    activity,
                    "Passwords don't match!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        btnDeleteAcc = view.findViewById(R.id.delete_acc)
        btnDeleteAcc.setOnClickListener {
            deleteAccount() {
                    isSuccess, errorMessage ->
                if (isSuccess){
                    val intent = Intent(activity, LogIn::class.java)
                    startActivity(intent)
                    activity?.finish()
                } else {
                    Toast.makeText(
                        activity,
                        errorMessage,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun changePassword(currentPassword: String, newPassword: String, onComplete: (Boolean, String?) -> Unit) {
        // Get the current authenticated user
        val currentUser = FirebaseAuth.getInstance().currentUser

        // Check if the user is signed in
        if (currentUser != null) {
            // Re-authenticate the user using their current password
            val credential = currentUser.email?.let {
                EmailAuthProvider.getCredential(it, currentPassword)
            }

            if (credential != null) {
                currentUser.reauthenticate(credential)
                    .addOnCompleteListener { reauthTask ->
                        if (reauthTask.isSuccessful) {
                            // Update the user's password
                            currentUser.updatePassword(newPassword)
                                .addOnCompleteListener { updatePasswordTask ->
                                    if (updatePasswordTask.isSuccessful) {
                                        onComplete(true, null) // Password updated successfully
                                    } else {
                                        onComplete(false, updatePasswordTask.exception?.message)
                                    }
                                }
                        } else {
                            onComplete(false, "Re-authentication failed. Please check your current password.")
                        }
                    }
            } else {
                onComplete(false, "Invalid credentials. Please try again.")
            }
        } else {
            onComplete(false, "User is not signed in.")
        }
    }

    private fun deleteAccount(onComplete: (Boolean, String?) -> Unit) {
        // Get the current authenticated user
        val currentUser = FirebaseAuth.getInstance().currentUser

        // Check if the user is signed in
        if (currentUser != null) {
            // Delete the user account
            currentUser.delete()
                .addOnCompleteListener { deleteTask ->
                    if (deleteTask.isSuccessful) {
                        onComplete(true, null) // Account deleted successfully
                    } else {
                        onComplete(false, deleteTask.exception?.message)
                    }
                }
        } else {
            onComplete(false, "User is not signed in.")
        }
    }
}