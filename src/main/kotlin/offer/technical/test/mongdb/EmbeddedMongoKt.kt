package offer.technical.test.mongdb

import de.flapdoodle.embed.mongo.Command
import de.flapdoodle.embed.mongo.MongodProcess
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.Defaults
import de.flapdoodle.embed.mongo.config.MongodConfig
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.config.RuntimeConfig
import de.flapdoodle.embed.process.config.io.ProcessOutput
import mu.KotlinLogging
import java.io.IOException
import java.net.Socket
import java.util.*

class EmbeddedMongoKt {

    private val log = KotlinLogging.logger {}

    private var mongodProcess: MongodProcess? = null
    private var host = "localhost"
    private var version = Version.Main.DEVELOPMENT
    private var port = 29019
    private var active = false

    /**
     * Creates a new EmbeddedMongoKt instance
     *
     * @return EmbeddedMongoKt instance
     */
    fun create(): EmbeddedMongoKt? {
        return EmbeddedMongoKt()
    }

    /**
     * Sets the port for the EmbeddedMongoKt instance
     *
     * Default is 29019
     *
     * @param port The port to set
     * @return EmbeddedMongoKt instance
     */
    fun withPort(port: Int): EmbeddedMongoKt {
        this.port = port
        return this
    }

    /**
     * Sets the host for the EmbeddedMongoKt instance
     *
     * Default is localhost
     *
     * @param host The host to set
     * @return EmbeddedMongoKt instance
     */
    fun withHost(host: String): EmbeddedMongoKt {
        Objects.requireNonNull(host, "host can not be null")
        this.host = host
        return this
    }

    /**
     * Sets the version for the EmbeddedMongoKt instance
     *
     * Default is Version.Main.PRODUCTION
     *
     * @param version The version to set
     * @return EmbeddedMongoKt instance
     */
    fun withVersion(version: Version.Main): EmbeddedMongoKt? {
        Objects.requireNonNull(version, "version can not be null")
        this.version = version
        return this
    }

    /**
     * Starts the EmbeddedMongoKt instance
     * @return EmbeddedMongoKt instance
     */
    fun start(): EmbeddedMongoKt? {
        if (!active && !inUse(host, port)) {
            try {
                val command = Command.MongoD
                val runtimeConfig: RuntimeConfig = Defaults.runtimeConfigFor(command)
                    .processOutput(ProcessOutput.getDefaultInstanceSilent())
                    .build()
                mongodProcess = MongodStarter.getInstance(runtimeConfig).prepare(
                    MongodConfig.builder()
                        .version(version)
                        .net(Net(host, port, false))
                        .build()
                )
                    .start()
                active = true
                log.info("Successfully started EmbeddedMongoKt @ {}:{}", host, port)
            } catch (e: IOException) {
                log.error("Failed to start EmbeddedMongoKt @ {}:{}", host, port, e)
            }
        }
        return this
    }


    /**
     * Checks if the given host and port is already in use
     *
     * @param host The host to check
     * @param port The port to check
     * @return True is port is in use, false otherwise
     */
    private fun inUse(host: String, port: Int): Boolean {
        var result = false
        try {
            Socket(host, port).close()
            result = true
        } catch (e: IOException) {
            log.warn("Did not (re-)start EmbeddedMongoKt @ {}:{} - looks like port is already in use?!", this.host, this.port, e)
        }
        return result
    }

    /**
     * Stops the EmbeddedMongoKt instance
     */
    fun stop() {
        if (active) {
            mongodProcess!!.stop()
            active = false
            log.info("Successfully stopped EmbeddedMongoKt @ {}:{}", host, port)
        }
    }

    fun getHost(): String? {
        return host
    }

    fun getVersion(): Version.Main? {
        return version
    }

    fun getPort(): Int {
        return port
    }

    fun isActive(): Boolean {
        return active
    }

}