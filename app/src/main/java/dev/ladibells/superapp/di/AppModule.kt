package dev.ladibells.superapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesSupabase() : SupabaseClient {
        val supabase = createSupabaseClient(
            supabaseUrl = "https://vobwbexaggapjdngfdcq.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZvYndiZXhhZ2dhcGpkbmdmZGNxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjY0MzQyMTQsImV4cCI6MjA4MjAxMDIxNH0._ZCqCysmajxs_dthjJYYwb4rlzGGx9jBmNXQwkSRksk"
        ) {
            install(plugin = Postgrest)
        }
        return supabase
    }
}