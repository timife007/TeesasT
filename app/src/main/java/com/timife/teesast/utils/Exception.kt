package com.timife.teesast.utils

import java.io.IOException

class NetworkUnavailableException(message: String = "No network available :(") : IOException(message)