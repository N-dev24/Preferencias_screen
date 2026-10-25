package rivera.noah.preferencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rivera.noah.preferencias.ui.theme.PreferenciasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PreferenciasTheme {
                PreferenciasScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenciasScreen() {
    // State for switches
    var recibirNotificaciones by remember { mutableStateOf(true) }
    var becarios by remember { mutableStateOf(true) }
    var aspirantes by remember { mutableStateOf(false) }
    var avisosGenerales by remember { mutableStateOf(true) }

    var selectedTab by remember { mutableStateOf(2) } // 2 for Preferencias

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Logo Box
                        Box(
                            modifier = Modifier
                                .size(width = 54.dp, height = 40.dp)
                                .background(
                                    color = Color(0xFF132F59),
                                    shape = RoundedCornerShape(4.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "FER",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        
                        Text(
                            text = "Preferencias",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF132F59)
                        )
                    }

                    // Notification bell with red dot
                    Box(modifier = Modifier.size(40.dp)) {
                        IconButton(
                            onClick = { /* Handle notification click */ },
                            modifier = Modifier
                                .align(Alignment.Center)
                                .background(Color(0xFFF0F4F8), CircleShape)
                                .size(40.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notificaciones",
                                tint = Color(0xFF132F59),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        // Notification dot
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color(0xFF132F59), CircleShape)
                                .align(Alignment.TopEnd)
                        )
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = Color(0xFF132F59)
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Campaign, contentDescription = "Anuncios") },
                    label = { Text("Anuncios") },
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF132F59),
                        selectedTextColor = Color(0xFF132F59),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
                    label = { Text("Inicio") },
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF132F59),
                        selectedTextColor = Color(0xFF132F59),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Preferencias") },
                    label = { Text("Preferencias") },
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF132F59),
                        selectedTextColor = Color(0xFF132F59),
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        },
        containerColor = Color(0xFFF8F9FC)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Personaliza los avisos que recibes",
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Section: Notificaciones
            Text(
                text = "Notificaciones",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF132F59)
            )

            Spacer(modifier = Modifier.height(12.dp))

            PreferenceSwitchItem(
                title = "Recibir notificaciones",
                subtitle = "Avisos importantes en tu celular",
                checked = recibirNotificaciones,
                onCheckedChange = { recibirNotificaciones = it }
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                thickness = 1.dp,
                color = Color(0xFFE5E7EB)
            )

            // Section: Temas de interés
            Text(
                text = "Temas de interés",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF132F59)
            )

            Spacer(modifier = Modifier.height(12.dp))

            PreferenceSwitchItem(
                title = "Becarios",
                subtitle = "Renovaciones y recordatorios",
                checked = becarios,
                onCheckedChange = { becarios = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            PreferenceSwitchItem(
                title = "Aspirantes",
                subtitle = "Convocatorias y solicitudes",
                checked = aspirantes,
                onCheckedChange = { aspirantes = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            PreferenceSwitchItem(
                title = "Avisos generales",
                subtitle = "Noticias y eventos",
                checked = avisosGenerales,
                onCheckedChange = { avisosGenerales = it }
            )
        }
    }
}

@Composable
fun PreferenceSwitchItem(
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF132F59)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                fontSize = 13.sp,
                color = Color(0xFF71717A)
            )
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF132F59),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFE4E4E7),
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 851)
@Composable
fun PreferenciasScreenPreview() {
    PreferenciasTheme {
        PreferenciasScreen()
    }
}
