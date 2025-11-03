package com.example.gamezone.views

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import java.io.File
import java.util.concurrent.Executors

class CameraScreen(private val onBackClick: (() -> Unit)? = null) {

    @Composable
    fun camara() {

        val context = LocalContext.current
        val lifecycle = LocalLifecycleOwner.current

        var tenemosPermisoCamara by remember {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            )
        }

        val lanzarPermiso = rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted -> tenemosPermisoCamara = granted }

        var camaraAbierta by remember { mutableStateOf(false) }
        var imagenUri by remember { mutableStateOf<Uri?>(null) }

        val ejecutarCamara = remember { Executors.newSingleThreadExecutor() }
        val capturaFoto = remember { ImageCapture.Builder().build() }

        val proveedorCamara = remember { ProcessCameraProvider.getInstance(context) }

        if (!tenemosPermisoCamara) {
            LaunchedEffect(Unit) {
                lanzarPermiso.launch(Manifest.permission.CAMERA)
            }
        }

        if (tenemosPermisoCamara && camaraAbierta) {

            Box(Modifier.fillMaxSize()) {

                AndroidView(
                    factory = { ctx ->
                        PreviewView(ctx).apply {
                            val cameraProvider = proveedorCamara.get()
                            val vistaPrevia = Preview.Builder().build().also {
                                it.setSurfaceProvider(this.surfaceProvider)
                            }

                            try {
                                cameraProvider.unbindAll()
                                cameraProvider.bindToLifecycle(
                                    lifecycle,
                                    CameraSelector.DEFAULT_BACK_CAMERA,
                                    vistaPrevia,
                                    capturaFoto
                                )
                            } catch (e: Exception) {}
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )

                // BOTÓN VOLVER (cuando cámara abierta)
                Button(
                    onClick = { onBackClick?.invoke() },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp)
                ) {
                    Text("Volver")
                }

                Button(
                    onClick = {
                        val archivoFoto =
                            File(context.cacheDir, "foto_${System.currentTimeMillis()}.jpg")
                        val salidaFoto =
                            ImageCapture.OutputFileOptions.Builder(archivoFoto).build()

                        capturaFoto.takePicture(
                            salidaFoto,
                            ejecutarCamara,
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                                    imagenUri = Uri.fromFile(archivoFoto)
                                    camaraAbierta = false
                                }

                                override fun onError(exception: ImageCaptureException) {}
                            }
                        )
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Text("Tomar foto")
                }
            }

        } else {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(onClick = {
                    if (tenemosPermisoCamara) camaraAbierta = true
                    else lanzarPermiso.launch(Manifest.permission.CAMERA)
                }) {
                    Text("Usar cámara")
                }

                Spacer(modifier = Modifier.height(24.dp))

                imagenUri?.let { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = "Foto",
                        modifier = Modifier.size(300.dp)
                    )
                }

                Spacer(Modifier.height(16.dp))

                // BOTÓN VOLVER (cuando NO está abierta la cámara)
                Button(onClick = { onBackClick?.invoke() }) {
                    Text("Volver")
                }
            }
        }
    }
}
