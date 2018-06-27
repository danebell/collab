package com.github.danebell.protests.utils

import java.io.{File, FileNotFoundException}
import java.nio.charset.StandardCharsets

import scala.io.BufferedSource
import scala.io.Source

//import org.slf4j.LoggerFactory

object FileUtils {
  //val logger = LoggerFactory.getLogger(this.getClass())
  val utf8 = StandardCharsets.UTF_8.toString

  def sourceFromResource(path: String): BufferedSource = {
    val url = FileUtils.getClass.getResource(path)

    if (url == null)
      throw newFileNotFoundException(path)
    //logger.info("Sourcing resource " + url.getPath())
    Source.fromURL(url, utf8)
  }

  def sourceFromFile(file: File): BufferedSource = {
    //logger.info("Sourcing file " + file.getPath())
    Source.fromFile(file, utf8)
  }

  def sourceFromFile(path: String): BufferedSource = sourceFromFile(new File(path))

  def newFileNotFoundException(path: String): FileNotFoundException = {
    val message1 = path + " (The system cannot find the path specified"
    val message2 = message1 + (if (path.startsWith("~")) ".  Make sure to not use the tilde (~) character in paths in lieu of the home directory." else "")
    val message3 = message2 + ")"

    new FileNotFoundException(message3)
  }

  protected def getTextFromSource(source: Source): String = {
    try {
      source.mkString
    }
    finally {
      source.close()
    }
  }

  def getTextFromResource(path: String): String =
    getTextFromSource(sourceFromResource(path))

}