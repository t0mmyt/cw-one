package sml
import scala.util.control.Breaks._

/**
  * This class ....
  *
  * @author someone
  */
case class BnzInstruction(label: String, opcode: String, register: Int, target: String) extends Instruction(label, opcode) {

  override def execute(m: Machine) =
    // is register 0?
    if (m.regs(register) != 0) {
      // find new instruction
      val goto = m.labels.find(target)
      if (goto >= 0)
        m.execute(goto)
      else
        throw new IllegalArgumentException(s"Error at ${label}: ${target} not found!")
      // This is not very functional but I can't find another way without modifying the main execution loop
      break
    }

  override def toString(): String = {
    super.toString + s" is register ${register} zero\n"
  }
}

object BnzInstruction {
  def apply(label: String, register: Int, target: String) =
    new BnzInstruction(label, "bnz", register, target)
}