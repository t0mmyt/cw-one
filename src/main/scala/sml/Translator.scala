package sml
/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
class Translator(fileName: String) {
  /**
    * translate the small program in the file into lab (the labels) and prog (the program)
    */
  def readAndTranslate(m: Machine): Machine = {
    val labels = m.labels
    var program = m.prog
    import scala.io.Source
    val lines = Source.fromFile(fileName).getLines
    for (line <- lines) {
      val fields = line.split(" ")
      if (fields.nonEmpty) {
        labels.add(fields(0))
        val this_instruction = "sml." + fields(1).capitalize + "Instruction"
        val instruction_args = fields.drop(2)
        val c_int = classOf[Int]
        val c_str = classOf[String]
        val instruction = instruction_args.length match {
          case 1 =>
            val c = Class.forName(this_instruction).getConstructor(c_str, c_str, c_int)
            c.newInstance(fields(0), fields(1), fields(2).toInt: Integer).asInstanceOf[Instruction]
          case 2 =>
            // fields(3) may be a string (in the case of a BNZ instruction) so handle that
            if (fields(3).forall(_.isDigit)) {
              // fields(3) was an integer
              val c = Class.forName(this_instruction).getConstructor(c_str, c_str, c_int, c_int)
              c.newInstance(fields(0), fields(1), fields(2).toInt: Integer, fields(3).toInt: Integer).asInstanceOf[Instruction]
            } else {
              // fields(3) was a string
              val c = Class.forName(this_instruction).getConstructor(c_str, c_str, c_int, c_str)
              c.newInstance(fields(0), fields(1), fields(2).toInt: Integer, fields(3)).asInstanceOf[Instruction]
            }
          case 3 =>
            val c = Class.forName(this_instruction).getConstructor(c_str, c_str, c_int, c_int, c_int)
            c.newInstance(fields(0), fields(1), fields(2).toInt: Integer, fields(3).toInt: Integer, fields(4).toInt: Integer).asInstanceOf[Instruction]
        }
        program = program :+ instruction
     }
    }
    new Machine(labels, program)
  }
}

object Translator {
  def apply(file: String) =
    new Translator(file)
}
