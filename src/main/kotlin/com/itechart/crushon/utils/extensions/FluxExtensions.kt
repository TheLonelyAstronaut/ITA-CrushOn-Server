package com.itechart.crushon.utils

import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferUtils
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.io.IOException
import java.io.InputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream


fun Flux<DataBuffer>.toInputStream(): InputStream {
    val osPipe = PipedOutputStream()
    val isPipe = PipedInputStream(osPipe)

    DataBufferUtils.write(this, osPipe)
        .subscribeOn(Schedulers.boundedElastic())
        .doOnComplete {
            try {
                osPipe.close()
            } catch (ignored: IOException) {
            }
        }
        .subscribe(DataBufferUtils.releaseConsumer())

    return isPipe
}
