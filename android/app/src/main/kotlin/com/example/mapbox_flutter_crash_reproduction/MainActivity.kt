package com.example.mapbox_flutter_crash_reproduction

import android.content.Context
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragmentActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MainActivity: FlutterActivity() {

    override fun shouldDestroyEngineWithHost(): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        ensureFlutterEngine(this)
        super.onCreate(savedInstanceState)
    }

    override fun getCachedEngineId(): String {
        return FLUTTER_CACHED_ENGINE_ID
    }

    companion object {
        // this mimics that the Flutter Engine survives the Activity's lifecycle
        const val FLUTTER_CACHED_ENGINE_ID = "SampleAppFlutterEngineId"

        fun ensureFlutterEngine(context: Context) {
            if(!FlutterEngineCache.getInstance().contains(FLUTTER_CACHED_ENGINE_ID)) {
                initFlutterEngine(context)
            }
        }

        private fun initFlutterEngine(context: Context) {
            val flutterEngine = FlutterEngine(context, null, true)
            FlutterEngineCache.getInstance().put(FLUTTER_CACHED_ENGINE_ID, flutterEngine)
            flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        }
    }
}
