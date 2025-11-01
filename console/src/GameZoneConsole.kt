import com.gamezone.models.GameGenre
import com.gamezone.models.User
import com.gamezone.data.UserRepository
import com.gamezone.data.LoginResult
import com.gamezone.data.RegistrationResult
import com.gamezone.viewmodels.RegisterViewModel
import com.gamezone.viewmodels.LoginViewModel

fun main() {
    println("╔═══════════════════════════════════════════════════════╗")
    println("║           GAMEZONE - Tienda de Videojuegos           ║")
    println("║        Sistema de Registro y Login (Versión CLI)     ║")
    println("╚═══════════════════════════════════════════════════════╝")
    println()
    
    var running = true
    
    while (running) {
        println("\n═══ MENÚ PRINCIPAL ═══")
        println("1. Registrar nuevo usuario")
        println("2. Iniciar sesión")
        println("3. Ver usuarios registrados")
        println("4. Salir")
        print("\nSeleccione una opción: ")
        
        when (readLine()?.trim()) {
            "1" -> registerUser()
            "2" -> loginUser()
            "3" -> viewUsers()
            "4" -> {
                println("\n¡Gracias por usar GameZone! ¡Hasta pronto!")
                running = false
            }
            else -> println("❌ Opción inválida. Por favor intente nuevamente.")
        }
    }
}

fun registerUser() {
    println("\n╔═══════════════════════════════════════════════════════╗")
    println("║                 REGISTRO DE USUARIO                   ║")
    println("╚═══════════════════════════════════════════════════════╝")
    
    val viewModel = RegisterViewModel()
    
    print("\n📝 Nombre Completo (solo letras y espacios, max 100 caracteres): ")
    viewModel.fullName = readLine()?.trim() ?: ""
    val nameValidation = viewModel.validateFullName()
    if (!nameValidation.isSuccess()) {
        println("❌ ${nameValidation.getErrorMessage()}")
        return
    }
    
    print("📧 Correo Electrónico (@duoc.cl, max 60 caracteres): ")
    viewModel.email = readLine()?.trim() ?: ""
    val emailValidation = viewModel.validateEmail()
    if (!emailValidation.isSuccess()) {
        println("❌ ${emailValidation.getErrorMessage()}")
        return
    }
    
    println("\n🔐 Contraseña (min 10 caracteres):")
    println("   ✓ Al menos 1 mayúscula")
    println("   ✓ Al menos 1 minúscula")
    println("   ✓ Al menos 1 número")
    println("   ✓ Al menos 1 carácter especial (@#\$%&*!?_-)")
    print("Ingrese contraseña: ")
    viewModel.password = readLine()?.trim() ?: ""
    val passwordValidation = viewModel.validatePassword()
    if (!passwordValidation.isSuccess()) {
        println("❌ ${passwordValidation.getErrorMessage()}")
        return
    }
    
    print("🔐 Confirme su contraseña: ")
    viewModel.confirmPassword = readLine()?.trim() ?: ""
    val confirmValidation = viewModel.validateConfirmPassword()
    if (!confirmValidation.isSuccess()) {
        println("❌ ${confirmValidation.getErrorMessage()}")
        return
    }
    
    print("📱 Teléfono (opcional, solo números 8-15 dígitos): ")
    viewModel.phone = readLine()?.trim() ?: ""
    if (viewModel.phone.isNotEmpty()) {
        val phoneValidation = viewModel.validatePhone()
        if (!phoneValidation.isSuccess()) {
            println("❌ ${phoneValidation.getErrorMessage()}")
            return
        }
    }
    
    println("\n🎮 Seleccione sus géneros favoritos (separados por comas):")
    GameGenre.getAllGenres().forEachIndexed { index, genre ->
        println("   ${index + 1}. ${genre.displayName}")
    }
    print("Ingrese números (ej: 1,3,4): ")
    val genreInput = readLine()?.trim() ?: ""
    val selectedIndices = genreInput.split(",").mapNotNull { it.trim().toIntOrNull() }
    
    selectedIndices.forEach { index ->
        if (index in 1..GameGenre.getAllGenres().size) {
            viewModel.selectedGenres.add(GameGenre.getAllGenres()[index - 1])
        }
    }
    
    val genreValidation = viewModel.validateGenres()
    if (!genreValidation.isSuccess()) {
        println("❌ ${genreValidation.getErrorMessage()}")
        return
    }
    
    when (val result = viewModel.registerUser()) {
        is RegistrationResult.Success -> {
            println("\n✅ ¡Registro exitoso!")
            println("\n" + result.user.toString())
        }
        is RegistrationResult.Error -> {
            println("\n❌ Error: ${result.message}")
        }
    }
}

fun loginUser() {
    println("\n╔═══════════════════════════════════════════════════════╗")
    println("║                  INICIO DE SESIÓN                     ║")
    println("╚═══════════════════════════════════════════════════════╝")
    
    val viewModel = LoginViewModel()
    
    print("\n📧 Correo Electrónico: ")
    viewModel.email = readLine()?.trim() ?: ""
    
    print("🔐 Contraseña: ")
    viewModel.password = readLine()?.trim() ?: ""
    
    when (val result = viewModel.loginUser()) {
        is LoginResult.Success -> {
            println("\n✅ ¡Inicio de sesión exitoso!")
            println("   ¡Bienvenido ${result.user.fullName}!")
            println("\n" + result.user.toString())
        }
        is LoginResult.Error -> {
            when {
                result.message.contains("no encontrado", ignoreCase = true) ->
                    println("\n❌ Usuario no encontrado. Verifica tu correo electrónico.")
                result.message.contains("incorrecta", ignoreCase = true) ->
                    println("\n❌ Contraseña incorrecta. Por favor intenta nuevamente.")
                else -> println("\n❌ Error: ${result.message}")
            }
        }
    }
}

fun viewUsers() {
    println("\n╔═══════════════════════════════════════════════════════╗")
    println("║              USUARIOS REGISTRADOS                     ║")
    println("╚═══════════════════════════════════════════════════════╝")
    
    val users = UserRepository.getAllUsers()
    
    if (users.isEmpty()) {
        println("\n⚠️  No hay usuarios registrados aún.")
    } else {
        println("\nTotal de usuarios: ${users.size}\n")
        users.forEachIndexed { index, user ->
            println("────────────────────────────────────────────────────────")
            println("Usuario #${index + 1}:")
            println("  Nombre: ${user.fullName}")
            println("  Email: ${user.email}")
            println("  Teléfono: ${user.phone ?: "No proporcionado"}")
            println("  Géneros: ${user.favoriteGenres.joinToString(", ") { it.displayName }}")
        }
        println("────────────────────────────────────────────────────────")
    }
}
